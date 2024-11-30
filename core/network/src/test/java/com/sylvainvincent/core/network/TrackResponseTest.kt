package com.sylvainvincent.core.network

import com.sylvainvincent.myalbums.core.model.Track
import com.sylvainvincent.myalbums.core.network.model.TrackResponse
import com.sylvainvincent.myalbums.core.network.model.toTrack
import org.junit.Test
import kotlin.test.assertEquals

class TrackResponseTest {

    @Test
    fun `given TrackResponse when toTrack then return Track`() {
        val trackResponse = TrackResponse(
            id = 5121,
            albumId = 9123,
            title = "agam",
            url = "https://www.google.com/#q=cetero",
            thumbnailUrl = "https://duckduckgo.com/?q=conceptam"
        ).toTrack()

        val track = Track(
            id = 5121,
            title = "agam",
            coverUrl = "https://www.google.com/#q=cetero"
        )
        assertEquals(expected = track, actual = trackResponse)
    }


}