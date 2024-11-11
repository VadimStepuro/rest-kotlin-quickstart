package org.acme.reactive.storage.service

import io.quarkus.hibernate.reactive.panache.common.WithTransaction
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.acme.model.CreateStorageModel
import org.acme.model.StorageModel
import org.acme.reactive.storage.repository.StorageEntityReactiveRepository
import org.acme.storage.mapper.StorageEntityMapper
import java.util.UUID
import kotlin.collections.map

@ApplicationScoped
class StorageEntityReactiveService {
    @Inject
    private lateinit var repository: StorageEntityReactiveRepository
    @Inject
    lateinit var mapper: StorageEntityMapper

    fun findById(id: UUID): Uni<StorageModel> {
        return repository.findById(id)
            .map { mapper.mapToStorageModel(it) }
    }

    fun findByName(name: String): Uni<List<StorageModel>> {
        return repository.findByName(name)
            .map {
                it.map {
                    mapper.mapToStorageModel(it)
                }
            }
    }

    @WithTransaction
    fun save(storageModel: CreateStorageModel): Uni<StorageModel> {
        
        val storageEntity = mapper.mapToStorageEntity(storageModel)

        return repository.persistAndFlush(storageEntity)
            .map { mapper.mapToStorageModel(it) }
    }
}