package org.acme.service

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

    fun findById(id: UUID): StorageDto {
        val storageModel = storageEntityStorageService.findById(id)
        return mapper.mapToStorageDto(storageModel)
    }

    fun findByName(name: String): List<StorageDto> {
        val storageModels = storageEntityStorageService.findByName(name)
        return storageModels.map { mapper.mapToStorageDto(it) }
    }

    fun save(storageDto: CreateStorageDto): StorageDto {
        val storageModel = mapper.mapToStorageModel(storageDto)
        val savedModel = storageEntityStorageService.save(storageModel)

        return mapper.mapToStorageDto(savedModel)
    }
}