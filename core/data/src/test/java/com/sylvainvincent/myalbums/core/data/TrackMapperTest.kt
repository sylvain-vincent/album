package com.sylvainvincent.myalbums.core.data

import com.sylvainvincent.myalbums.core.database.model.TrackEntity
import com.sylvainvincent.myalbums.core.network.model.TrackResponse
import org.junit.Test
import kotlin.test.assertEquals

class TrackMapperTest {

    @Test
    fun `given trackResponse when toTrackEntity then return TrackEntity`() {

        val actual = TrackResponse(
            id = 2839,
            albumId = 7692,
            title = "adhuc",
            url = "https://duckduckgo.com/?q=dolorum",
            thumbnailUrl = "https://search.yahoo.com/search?p=invidunt"
        ).toTrackEntity()

        val expected = TrackEntity(
            id = 2839,
            title = "adhuc",
            coverUrl = "https://duckduckgo.com/?q=dolorum",
            thumbnailUrl = "https://search.yahoo.com/search?p=invidunt"

        )

        assertEquals(expected = expected, actual = actual)
    }

}