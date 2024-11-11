package org.acme.kafka

import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import kotlinx.serialization.json.Json
import org.acme.model.CreationEvent
import org.acme.model.StorageModel
import org.acme.service.mapper.CreationEventMapper
import org.acme.storage.StorageEntityStorageService
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.jboss.logging.Logger

@ApplicationScoped
class KafkaCreationListener {

    private val logger = Logger.getLogger(KafkaCreationListener::class.java)

    @Inject
    private lateinit var eventMapper: CreationEventMapper
    @Inject
    private lateinit var storageService: StorageEntityStorageService
    @Inject
    private lateinit var notifier: KafkaCreationNotifier

    @Incoming("creation-event")
    @WithTransaction
    fun consumeCreationEvent(message: String) : Uni<StorageModel> {

        logger.info("Starting processing message : $message")
        val event = Json.decodeFromString<CreationEvent>(message)

        val storageModel = eventMapper.mapToStorageModel(event)
        val save = storageService.save(storageModel)
        logger.info("Saved storage model $storageModel")

        notifier.notifyCreation(event)
        logger.info("Finished processing message : $message")

        return save
    }
}