package org.acme.web

import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.acme.service.StorageModelService
import org.acme.web.dto.StorageDto
import org.jboss.resteasy.reactive.RestPath
import java.util.*

@Path("/api/storage")
class StorageDtoController {
    @Inject
    lateinit var service: StorageModelService;

    @GET
    fun findById(id: UUID) = service.findById(id)

    @GET
    @Path("/name/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun findByName(@RestPath name: String) = service.findByName(name)

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun save(storageDto: StorageDto) = service.save(storageDto)
}