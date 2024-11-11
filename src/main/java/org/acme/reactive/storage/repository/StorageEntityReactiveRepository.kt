package org.acme.reactive.storage.repository

import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.smallrye.mutiny.Uni
import jakarta.enterprise.context.ApplicationScoped
import org.acme.model.db.StorageEntity
import java.util.UUID

@ApplicationScoped
class StorageEntityReactiveRepository : PanacheRepository<StorageEntity> {

    fun findByName(name: String): Uni<List<StorageEntity>> = list("name", name)

    fun findById(id: UUID): Uni<StorageEntity> = find("id", id).firstResult()

}