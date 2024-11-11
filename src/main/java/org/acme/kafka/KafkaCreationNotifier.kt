package org.acme.kafka


import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.acme.model.CreationEvent
import org.acme.service.mapper.CreationEventMapper
import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter
import org.jboss.logging.Logger

@ApplicationScoped
class KafkaCreationNotifier {

    private val logger = Logger.getLogger(KafkaCreationNotifier::class.java)

    @Inject
    private lateinit var creationEventMapper: CreationEventMapper

    @Inject
    @Channel("creation-notify")
    private lateinit var notifyEmitter: Emitter<String>

    fun notifyCreation(messageToNotify: CreationEvent) {

        val message = creationEventMapper.mapToStorageModel(messageToNotify)
        val messagePayload = Json.encodeToString(message)

        notifyEmitter.send(messagePayload).toCompletableFuture().join()
        logger.info("Sent message for other systems: $messagePayload")
    }
}