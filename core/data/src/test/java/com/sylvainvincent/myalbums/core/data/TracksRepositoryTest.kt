package com.sylvainvincent.myalbums.core.data

import com.sylvainvincent.myalbums.core.data.repository.TracksRepositoryImpl
import com.sylvainvincent.myalbums.core.database.dao.TrackDao
import com.sylvainvincent.myalbums.core.database.model.TrackEntity
import com.sylvainvincent.myalbums.core.model.Track
import com.sylvainvincent.myalbums.core.network.exception.NoNetworkException
import com.sylvainvincent.myalbums.core.network.model.TrackResponse
import com.sylvainvincent.myalbums.core.network.retrofit.RetrofitTracksNetworkApi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class TracksRepositoryTest {
    private val dispatcher = UnconfinedTestDispatcher()

    private val trackDaoMock = mockk<TrackDao>()
    private val tracksNetworkApiMock = mockk<RetrofitTracksNetworkApi>()

    private val tracksRepository = TracksRepositoryImpl(
        tracksNetworkApi = tracksNetworkApiMock,
        ioDispatcher = dispatcher,
        trackDao = trackDaoMock,
    )

    @Test
    fun `check network call and database insertion when fetchTracks`() = runBlocking {

        val trackResponse = TrackResponse(
            id = 7314,
            albumId = 3932,
            title = "class",
            url = "https://www.google.com/#q=verear",
            thumbnailUrl = "https://search.yahoo.com/search?p=congue"
        )

        val track = Track(
            id = 7314,
            albumId = 3932,
            title = "class",
            coverUrl = "https://www.google.com/#q=verear",
            thumbnailUrl = "https://search.yahoo.com/search?p=congue"
        )

        coEvery { tracksNetworkApiMock.fetchTracks() } returns listOf(trackResponse)
        coEvery { trackDaoMock.insertTracks(any()) } returns listOf(1)

        val actual = tracksRepository.fetchTracks()

        val expected = Result.success(listOf(track))

        coVerify(exactly = 1) { tracksNetworkApiMock.fetchTracks() }
        coVerify(exactly = 1) { trackDaoMock.insertTracks(any()) }
        coVerify(exactly = 0) { trackDaoMock.getTracksEntity() }
        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun `no internet when network call and database insertion from fetchTracks`() = runBlocking {

        val trackResponse = TrackEntity(
            id = 6318,
            albumId = 4322,
            title = "noster",
            coverUrl = "https://duckduckgo.com/?q=civibus",
            thumbnailUrl = "https://www.google.com/#q=vivendo"
        )

        val track = Track(
            id = 6318,
            albumId = 4322,
            title = "noster",
            coverUrl = "https://duckduckgo.com/?q=civibus",
            thumbnailUrl = "https://www.google.com/#q=vivendo"
        )

        coEvery { tracksNetworkApiMock.fetchTracks() } throws NoNetworkException("Network Error")
        coEvery { trackDaoMock.getTracksEntity() } returns listOf(trackResponse)

        val actual = tracksRepository.fetchTracks()

        val expected = Result.success(listOf(track))

        coVerify(exactly = 1) { tracksNetworkApiMock.fetchTracks() }
        coVerify(exactly = 0) { trackDaoMock.insertTracks(any()) }
        coVerify(exactly = 1) { trackDaoMock.getTracksEntity() }
        assertEquals(expected = expected, actual = actual)
    }

    @Test
    fun `no security when network call and database insertion from fetchTracks`() = runBlocking {

        val trackResponse = TrackEntity(
            id = 6318,
            albumId = 6220,
            title = "noster",
            coverUrl = "https://duckduckgo.com/?q=civibus",
            thumbnailUrl = "https://www.google.com/#q=vivendo"
        )

        val securityException = SecurityException("Security Error")

        coEvery { tracksNetworkApiMock.fetchTracks() } throws securityException
        coEvery { trackDaoMock.getTracksEntity() } returns listOf(trackResponse)

        val actual = tracksRepository.fetchTracks()

        val expected : Result<List<Track>> = Result.failure(securityException)

        coVerify(exactly = 1) { tracksNetworkApiMock.fetchTracks() }
        coVerify(exactly = 0) { trackDaoMock.insertTracks(any()) }
        coVerify(exactly = 0) { trackDaoMock.getTracksEntity() }
        assertEquals(expected = expected, actual = actual)

    }

}