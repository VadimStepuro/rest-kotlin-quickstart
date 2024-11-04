package org.acme.model

import kotlinx.serialization.Serializable

@Serializable
data class CreationNotifyEvent(
    val name: String
)
