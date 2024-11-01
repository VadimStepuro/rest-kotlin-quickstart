package org.acme.storage.repository

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import org.acme.model.db.StorageEntity
import java.util.*
import kotlin.collections.List

@ApplicationScoped
class StorageEntityRepository : PanacheRepositoryBase<StorageEntity, UUID> {

    fun findByName(name: String): List<StorageEntity> = list("name", name)

}