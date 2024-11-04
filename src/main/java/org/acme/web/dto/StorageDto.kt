package org.acme.web.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.util.*

data class StorageDto(
    @JsonProperty("id")
    var id: UUID,
    @JsonProperty("name")
    var name: String,
    @JsonProperty("description")
    var description: String,
    @JsonProperty("created")
    var created: LocalDateTime,
    @JsonProperty("updated")
    var updated: LocalDateTime
)
