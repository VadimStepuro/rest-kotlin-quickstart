package org.acme.web.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateStorageDto(
    @field:JsonProperty("name")
    val name: String,
    @field:JsonProperty("description")
    val description: String
)
