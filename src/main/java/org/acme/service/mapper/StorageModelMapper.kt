package org.acme.service.mapper

import jakarta.enterprise.context.ApplicationScoped
import org.acme.model.StorageModel
import org.acme.web.dto.StorageDto

@ApplicationScoped
class StorageModelMapper {

    fun mapToStorageDto(storageModel: StorageModel): StorageDto {
        return StorageDto(
            id = storageModel.id,
            name = storageModel.name,
            description = storageModel.description,
            created = storageModel.created,
            updated = storageModel.updated
        )
    }

    fun mapToStorageModel(storageDto: StorageDto): StorageModel {
        return StorageModel(
            id = storageDto.id,
            name = storageDto.name,
            description = storageDto.description,
            created = storageDto.created,
            updated = storageDto.updated
        )
    }
}