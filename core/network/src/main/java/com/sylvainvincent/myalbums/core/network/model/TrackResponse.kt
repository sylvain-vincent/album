package com.sylvainvincent.myalbums.core.network.model

import kotlinx.serialization.Serializable

@Serializable
class TrackResponse (
    val id: Int,
    val albumId: Int,
    val title: String,
    val coverUrl: String,
    val thumbnailUrl: String,
)

