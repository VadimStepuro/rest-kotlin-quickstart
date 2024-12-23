package org.acme.storage

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import org.acme.model.CreateStorageModel
import org.acme.model.StorageModel
import org.acme.storage.mapper.StorageEntityMapper
import org.acme.storage.repository.StorageEntityRepository
import java.util.*

@ApplicationScoped
class StorageEntityStorageService {

    @Inject
    lateinit var repository: StorageEntityRepository
    @Inject
    lateinit var mapper: StorageEntityMapper

    fun findById(id: UUID): StorageModel {
        val storageEntity = repository.findById(id)

        return mapper.mapToStorageModel(storageEntity)
    }

    fun findByName(name: String): List<StorageModel> {
        val storageEntities = repository.findByName(name)

        return storageEntities.map { mapper.mapToStorageModel(it) }
    }

    @Transactional
    fun save(storageModel: CreateStorageModel): StorageModel {
        val storageEntity = mapper.mapToStorageEntity(storageModel)
        repository.persistAndFlush(storageEntity)

        return mapper.mapToStorageModel(storageEntity)
    }
}
