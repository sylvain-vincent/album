package com.sylvainvincent.myalbums.core.network.model

import com.sylvainvincent.myalbums.core.model.Track
import kotlinx.serialization.Serializable

@Serializable
class TrackResponse (
    val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
)

fun TrackResponse.toTrack() = Track(
    id = this.id,
    title = this.title,
    coverUrl = this.url,
    thumbnailUrl = this.thumbnailUrl,
)

