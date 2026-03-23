package com.madev.marketplace.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("user_id") val userId: String = "",
    @SerialName("full_name") val fullName: String? = null,
    @SerialName("avatar_url") val avatarUrl: String? = null,
    @SerialName("onesignal_id") val oneSignalId: String? = null,
    @SerialName("created_at") val createdAt: String? = null
)
