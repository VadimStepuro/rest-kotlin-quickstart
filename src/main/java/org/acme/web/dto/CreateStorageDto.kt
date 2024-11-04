package org.acme.web.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateStorageDto(
    @JsonProperty("name")
    var name: String,
    @JsonProperty("description")
    var description: String
)
