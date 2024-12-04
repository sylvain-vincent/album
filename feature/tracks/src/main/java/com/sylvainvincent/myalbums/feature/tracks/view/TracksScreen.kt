package com.sylvainvincent.myalbums.feature.tracks.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
    val tracksState = viewModel.trackState.collectAsState().value

    val fetchOngoingMessage = stringResource(R.string.track_network_synchro_loading)
    val fetchErrorMessage = stringResource(R.string.track_network_synchro_error)
    val fetchSuccessMessage = stringResource(R.string.track_network_synchro_success)
    // val networkOfflineMessage = stringResource(R.string.track_network_offline)

    var isRefreshing by remember { mutableStateOf(false) }

    val event = viewModel.event.collectAsState().value

    LaunchedEffect(event) {
        when(event) {
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
        }
    }

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = {
            viewModel.fetchTracks()
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(innerPaddingValues)
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            when (tracksState) {
                is TracksState.Loaded -> {
                    items(items = tracksState.trackList) { track ->
                        TrackCell(trackTitle = track.title, thumbnailUrl = track.thumbnailUrl)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
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
