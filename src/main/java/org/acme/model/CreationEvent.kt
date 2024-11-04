package org.acme.model

import kotlinx.serialization.Serializable

@Serializable
data class CreationEvent(
    var name: String,
    var description: String
)
