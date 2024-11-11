package org.acme.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateStorageModel(
    val name: String,
    val description: String
)
