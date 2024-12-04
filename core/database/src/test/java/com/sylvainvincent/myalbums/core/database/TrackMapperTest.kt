package com.sylvainvincent.myalbums.core.database

import com.sylvainvincent.myalbums.core.database.model.TrackEntity
import com.sylvainvincent.myalbums.core.database.model.toTrack
import com.sylvainvincent.myalbums.core.database.model.toTrackEntity
import com.sylvainvincent.myalbums.core.model.Track
import org.junit.Test
import kotlin.test.assertEquals

class TrackMapperTest {

    @Test
    fun `given TrackEntity when toTrack then return Track`() {
        val trackResponse = TrackEntity(
            id = 5121,
            albumId = 1129,
            title = "agam",
            coverUrl = "https://www.google.com/#q=cetero",
            thumbnailUrl = "https://duckduckgo.com/?q=conceptam"
        ).toTrack()

        val track = Track(
            id = 5121,
            albumId = 1129,
            title = "agam",
            coverUrl = "https://www.google.com/#q=cetero",
            thumbnailUrl = "https://duckduckgo.com/?q=conceptam"
        )
        assertEquals(expected = track, actual = trackResponse)
    }

    @Test
    fun `given Track when toTrack then return TrackEntity`() {

        val track = Track(
            id = 5121,
            albumId = 6129,
            title = "agam",
            coverUrl = "https://www.google.com/#q=cetero",
            thumbnailUrl = "https://duckduckgo.com/?q=conceptam"
        ).toTrackEntity()

        val trackResponse = TrackEntity(
            id = 5121,
            albumId = 6129,
            title = "agam",
            coverUrl = "https://www.google.com/#q=cetero",
            thumbnailUrl = "https://duckduckgo.com/?q=conceptam"
        )
        assertEquals(expected = track, actual = trackResponse)
    }

}