package org.acme.service

import io.quarkus.hibernate.reactive.panache.common.WithSession
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.acme.service.mapper.StorageModelMapper
import org.acme.storage.StorageEntityStorageService
import org.acme.web.dto.CreateStorageDto
import org.acme.web.dto.StorageDto
import java.util.*

@ApplicationScoped
class StorageModelService {
    @Inject
    lateinit var storageEntityStorageService: StorageEntityStorageService
    @Inject
    lateinit var mapper: StorageModelMapper

    @WithSession
    fun findById(id: UUID): Uni<StorageDto> {
        return storageEntityStorageService.findById(id).map {
            mapper.mapToStorageDto(it)
        }
    }

    @WithSession
    fun findByName(name: String): Uni<List<StorageDto>> {
        return storageEntityStorageService.findByName(name).map {
            it.map { mapper.mapToStorageDto(it) }
        }
    }

    fun save(storageDto: CreateStorageDto): Uni<StorageDto> {
        val storageModel = mapper.mapToStorageModel(storageDto)
        return storageEntityStorageService.save(storageModel).map {
            mapper.mapToStorageDto(it)
        }
    }
}