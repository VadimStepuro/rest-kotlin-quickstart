package org.acme.model.db

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "storage_entity")
open class StorageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    var id: UUID? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

    @Column(name = "description", nullable = false)
    var description: String? = null

    @Column(name = "created", nullable = false)
    @CreationTimestamp
    var created: LocalDateTime? = null

    @Column(name = "updated", nullable = false)
    @UpdateTimestamp
    var updated: LocalDateTime? = null
}
