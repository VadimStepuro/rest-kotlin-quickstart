package org.acme.model

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class CreationEvent(
    @field:JsonProperty("name")
    val name: String,
    @field:JsonProperty("description")
    val description: String
)
