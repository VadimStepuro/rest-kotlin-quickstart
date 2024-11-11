package org.acme.storage.repository

import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import org.acme.model.db.StorageEntity
import java.util.*
import kotlin.collections.List

@ApplicationScoped
class StorageEntityRepository : PanacheRepository<StorageEntity> {

    fun findById(id: UUID): Uni<StorageEntity> = find("id", id).firstResult()

    fun findByName(name: String): Uni<List<StorageEntity>> = list("name", name)

}