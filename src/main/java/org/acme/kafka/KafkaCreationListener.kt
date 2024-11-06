package org.acme.kafka

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import kotlinx.serialization.json.Json
import org.acme.model.CreationEvent
import org.acme.service.mapper.CreationEventMapper
import org.acme.storage.StorageEntityStorageService
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.eclipse.microprofile.reactive.messaging.Message
import java.util.concurrent.CompletionStage
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
    @Transactional
    fun consumeCreationEvent(message: Message<String>) : CompletionStage<Void> {

        logger.info("Starting processing message : $message")
        val event = Json.decodeFromString<CreationEvent>(message.payload)

        val storageModel = eventMapper.mapToStorageModel(event)
        storageService.save(storageModel)
        logger.info("Saved storage model $storageModel")

        notifier.notifyCreation(event)
        logger.info("Finished processing message : $message")

        return message.ack()
    }
}