package org.acme.kafka

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.acme.model.CreationEvent
import org.acme.model.CreationNotifyEvent
import org.acme.service.mapper.CreationEventMapper

@ApplicationScoped
class KafkaCreationNotifier {

    @Inject
    lateinit var creationEventMapper: CreationEventMapper;

    fun notifyCreation(messageToNotify: CreationEvent) : CreationNotifyEvent {
        return creationEventMapper.mapToNotifyEvent(messageToNotify);
    }
}