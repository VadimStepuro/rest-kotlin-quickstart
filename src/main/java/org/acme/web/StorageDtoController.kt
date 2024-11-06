package org.acme.web

import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.acme.service.StorageModelService
import org.acme.web.dto.CreateStorageDto
import org.jboss.resteasy.reactive.RestPath
import java.util.*

@Path("/api/storage")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class StorageDtoController {
    @Inject
    private lateinit var service: StorageModelService

    @GET
    @Path("/{id}")
    fun findById(@RestPath id: UUID) = service.findById(id)

    @GET
    @Path("/name/{name}")
    fun findByName(@RestPath name: String) = service.findByName(name)

    @POST
    fun save(storageDto: CreateStorageDto) = service.save(storageDto)
}