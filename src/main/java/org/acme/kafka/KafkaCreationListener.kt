package org.acme.kafka

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import kotlinx.serialization.json.Json
import org.acme.model.CreationEvent
import org.acme.service.mapper.CreationEventMapper
import org.acme.storage.StorageEntityStorageService
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.eclipse.microprofile.reactive.messaging.Message
import java.util.concurrent.CompletionStage

@ApplicationScoped
class KafkaCreationListener {

    @Inject
    lateinit var eventMapper: CreationEventMapper;
    @Inject
    lateinit var storageService: StorageEntityStorageService
    @Inject
    lateinit var notifier: KafkaCreationNotifier

    @Incoming("creation-event")
    fun consumeCreationEvent(message: Message<String>) : CompletionStage<Void> {
        val event = Json.decodeFromString<CreationEvent>(message.payload);

        val storageModel = eventMapper.mapToStorageModel(event);
        storageService.save(storageModel);
        notifier.notifyCreation(event);

        return message.ack();
    }
}