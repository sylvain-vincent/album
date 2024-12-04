package com.sylvainvincent.myalbums.feature.tracks.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sylvainvincent.myalbums.core.model.Track
import com.sylvainvincent.myalbums.feature.tracks.Event
import com.sylvainvincent.myalbums.feature.tracks.R
import com.sylvainvincent.myalbums.feature.tracks.TracksState
import com.sylvainvincent.myalbums.feature.tracks.TracksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TracksScreenStateful(
    viewModel: TracksViewModel = hiltViewModel(),
    innerPaddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
) {
    val tracksState by viewModel.trackState.collectAsStateWithLifecycle()
    val event by viewModel.event.collectAsStateWithLifecycle()

    val fetchOngoingMessage = stringResource(R.string.track_network_synchro_loading)
    val fetchErrorMessage = stringResource(R.string.track_network_synchro_error)
    val fetchSuccessMessage = stringResource(R.string.track_network_synchro_success)
    val networkOfflineMessage = stringResource(R.string.track_network_offline)

    var isRefreshing by remember { mutableStateOf(false) }

    LaunchedEffect(event) {

        when (event) {
            Event.UNINITIALISED -> {
                isRefreshing = false
            }

            Event.LOADING -> {
                isRefreshing = true
                snackbarHostState.showSnackbar(fetchOngoingMessage)
            }

            Event.ERROR -> {
                isRefreshing = false
                snackbarHostState.showSnackbar(fetchErrorMessage)
            }

            Event.FETCH_SUCCESSFUL -> {
                isRefreshing = false
                snackbarHostState.showSnackbar(fetchSuccessMessage)
            }

            Event.NO_INTERNET -> {
                isRefreshing = false
                snackbarHostState.showSnackbar(networkOfflineMessage)
            }
        }
    }

    PullToRefreshBox(
        modifier = Modifier.padding(top = 20.dp),
        isRefreshing = isRefreshing,
        onRefresh = {
            viewModel.fetchTracks()
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(innerPaddingValues)
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            when (tracksState) {
                is TracksState.Loaded -> {
                    trackListScreen(
                        trackList = (tracksState as TracksState.Loaded).trackList,
                    )
                }

                is TracksState.Error -> {
                    item {
                        Text(stringResource(R.string.track_screen_error))
                    }
                }

                else -> {
                    item {
                        Text(stringResource(R.string.track_screen_welcome))
                    }
                }
            }
        }
    }
}

private fun LazyListScope.trackListScreen(trackList: List<Track>) {
    items(items = trackList) { track ->
        TrackCell(trackTitle = track.title, thumbnailUrl = track.thumbnailUrl)
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Preview
@Composable
private fun TrackListScreenPreview() {
    val trackList = listOf(
        Track(
            id = 1901,
            albumId = 4773,
            title = "dicant",
            coverUrl = "https://www.google.com/#q=blandit",
            thumbnailUrl = "http://www.bing.com/search?q=ligula"
        ),
        Track(
            id = 2825,
            albumId = 5923,
            title = "dicant",
            coverUrl = "https://duckduckgo.com/?q=singulis",
            thumbnailUrl = "https://duckduckgo.com/?q=mnesarchum"
        ),
        Track(
            id = 4587,
            albumId = 8827,
            title = "epicurei",
            coverUrl = "https://www.google.com/#q=sem",
            thumbnailUrl = "https://search.yahoo.com/search?p=sea"
        )
    )
    LazyColumn {
        trackListScreen(trackList = trackList)
    }
}