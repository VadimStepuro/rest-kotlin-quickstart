package org.acme.service.mapper

import jakarta.enterprise.context.ApplicationScoped
import org.acme.model.CreateStorageModel
import org.acme.model.CreationEvent
import org.acme.model.CreationNotifyEvent

@ApplicationScoped
class CreationEventMapper {
    fun mapToStorageModel(event: CreationEvent): CreateStorageModel {
        return CreateStorageModel(
            name = event.name,
            description = event.description
        );
    }

    fun mapToNotifyEvent(event: CreationEvent): CreationNotifyEvent {
        return CreationNotifyEvent(
            name = event.name
        )
    }
}