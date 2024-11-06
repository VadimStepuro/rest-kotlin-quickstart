package org.acme.web.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.util.*

data class StorageDto(
    @field:JsonProperty("id")
    val id: UUID,
    @field:JsonProperty("name")
    val name: String,
    @field:JsonProperty("description")
    val description: String,
    @field:JsonProperty("created")
    val created: LocalDateTime,
    @field:JsonProperty("updated")
    val updated: LocalDateTime
)
