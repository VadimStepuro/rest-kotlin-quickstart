package org.acme.kafka


import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.acme.model.CreationEvent
import org.acme.service.mapper.CreationEventMapper
import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter

@ApplicationScoped
class KafkaCreationNotifier {

    @Inject
    lateinit var creationEventMapper: CreationEventMapper;

    @Inject
    @Channel("creation-notify")
    private lateinit var notifyEmitter: Emitter<String>;

    fun notifyCreation(messageToNotify: CreationEvent) {
        val event = creationEventMapper.mapToNotifyEvent(messageToNotify);
        val messagePayload = Json.encodeToString(event);

        notifyEmitter.send(messagePayload);
    }
}