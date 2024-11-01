package org.acme.model

import java.time.LocalDateTime
import java.util.UUID

data class StorageModel(
    var id: UUID,
    var name: String,
    var description: String,
    var created: LocalDateTime,
    var updated: LocalDateTime
)
