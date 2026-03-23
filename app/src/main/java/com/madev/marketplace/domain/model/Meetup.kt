package com.madev.marketplace.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meetup(
    @SerialName("meetup_id") val meetupId: String = "",
    val title: String = "",
    val description: String? = null,
    val lat: Double? = null,
    val lng: Double? = null,
    val location: String? = null,
    @SerialName("starts_at") val startsAt: String? = null,
    @SerialName("is_active") val isActive: Boolean = true
)
