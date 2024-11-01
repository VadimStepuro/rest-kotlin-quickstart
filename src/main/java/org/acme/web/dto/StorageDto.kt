package org.acme.web.dto

import java.time.LocalDateTime
import java.util.*

data class StorageDto(
    var id: UUID,
    var name: String,
    var description: String,
    var created: LocalDateTime,
    var updated: LocalDateTime
)
