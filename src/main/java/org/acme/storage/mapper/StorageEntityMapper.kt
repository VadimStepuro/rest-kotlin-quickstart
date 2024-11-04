package org.acme.storage.mapper

import jakarta.enterprise.context.ApplicationScoped
import org.acme.model.CreateStorageModel
import org.acme.model.StorageModel
import org.acme.model.db.StorageEntity

@ApplicationScoped
class StorageEntityMapper {

    fun mapToStorageEntity(storageModel: StorageModel): StorageEntity {
        val storageEntity = StorageEntity();

        storageEntity.name = storageModel.name;
        storageEntity.description = storageModel.description;
        return storageEntity;
    }

    fun mapToStorageEntity(storageModel: CreateStorageModel): StorageEntity {
        val storageEntity = StorageEntity();

        storageEntity.name = storageModel.name;
        storageEntity.description = storageModel.description;
        return storageEntity;
    }

    fun mapToStorageModel(storageEntity: StorageEntity): StorageModel {
        return StorageModel(
            id = storageEntity.id!!,
            name = storageEntity.name!!,
            description = storageEntity.description!!,
            created = storageEntity.created!!,
            updated = storageEntity.updated!!,
        );
    }
}