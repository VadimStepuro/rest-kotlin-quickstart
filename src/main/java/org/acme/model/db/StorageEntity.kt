package org.acme.model.db

import io.quarkus.hibernate.orm.panache.PanacheEntityBase
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "message_to_send")
class StorageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null

    @Column(name ="name")
    var name: String? = null

    @Column(name ="description")
    var description: String? = null

    @Column(name ="created")
    @CreationTimestamp
    var created: LocalDateTime? = null

    @Column(name ="updated")
    @UpdateTimestamp
    var updated: LocalDateTime? = null
}
