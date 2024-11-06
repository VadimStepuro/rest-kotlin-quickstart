package org.acme.model

import java.time.LocalDateTime
import java.util.UUID

data class StorageModel(
    val id: UUID,
    val name: String,
    val description: String,
    val created: LocalDateTime,
    val updated: LocalDateTime
)
