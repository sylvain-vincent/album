package com.sylvainvincent.myalbums.core.model

data class Track(
    val id: Int,
    val albumId: Int,
    val title: String,
    val coverUrl: String,
    val thumbnailUrl: String,
)
