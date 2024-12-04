package com.sylvainvincent.myalbums.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sylvainvincent.myalbums.core.model.Track

@Entity(tableName = "track")
data class TrackEntity(
    @PrimaryKey
    val id: Int,
    val albumId: Int,
    val title: String,
    @ColumnInfo(defaultValue = "")
    val coverUrl: String,
    @ColumnInfo(defaultValue = "")
    val thumbnailUrl: String,
)

fun TrackEntity.toTrack() = Track(
    id = this.id,
    albumId = this.albumId,
    title = this.title,
    coverUrl = this.coverUrl,
    thumbnailUrl = this.thumbnailUrl
)

fun Track.toTrackEntity() = TrackEntity(
    id = this.id,
    albumId = this.albumId,
    title = this.title,
    coverUrl = this.coverUrl,
    thumbnailUrl = this.thumbnailUrl
)