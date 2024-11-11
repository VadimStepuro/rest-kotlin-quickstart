package org.acme.storage

import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
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

    fun findById(id: UUID): Uni<StorageModel> {
        return repository.findById(id).map {
            mapper.mapToStorageModel(it)
        }

    }

    fun findByName(name: String): Uni<List<StorageModel>> {
        return repository.findByName(name).map {
            it.map { mapper.mapToStorageModel(it) }
        }
    }

    @WithTransaction
    fun save(storageModel: CreateStorageModel): Uni<StorageModel> {
        val storageEntity = mapper.mapToStorageEntity(storageModel)
        return repository.persistAndFlush(storageEntity).map { mapper.mapToStorageModel(it) }
    }
}
