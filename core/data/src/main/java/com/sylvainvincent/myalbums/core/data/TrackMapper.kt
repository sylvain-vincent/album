package com.sylvainvincent.myalbums.core.data

import com.sylvainvincent.myalbums.core.database.model.TrackEntity
import com.sylvainvincent.myalbums.core.network.model.TrackResponse

fun TrackResponse.toTrackEntity() = TrackEntity(
    id = this.id,
    title = this.title,
    coverUrl = this.url,
    thumbnailUrl = this.thumbnailUrl
)