package com.sylvainvincent.myalbums.feature.tracks

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TracksScreenStateful(
    viewModel: TracksViewModel = hiltViewModel(),
    innerPaddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
) {
    val tracksState = viewModel.trackState.collectAsState()

    val networkLoadingMessage = stringResource(R.string.track_network_synchro_loading)
    val networkErrorMessage = stringResource(R.string.track_network_synchro_error)
    val networkSuccessMessage = stringResource(R.string.track_network_synchro_success)
    // val networkOfflineMessage = stringResource(R.string.track_network_offline)

    var isRefreshing by remember { mutableStateOf(false) }

    LaunchedEffect(tracksState.value) {
        when (tracksState.value) {
            TracksState.Loading -> {
                isRefreshing = true
                snackbarHostState.showSnackbar(networkLoadingMessage)
            }

            TracksState.Error -> {
                isRefreshing = false
                snackbarHostState.showSnackbar(networkErrorMessage)
            }

            is TracksState.Loaded -> {
                isRefreshing = false
                snackbarHostState.showSnackbar(networkSuccessMessage)
            }

            else -> {
                isRefreshing = false
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
                .padding(horizontal = 6.dp)
                .padding(top = 12.dp, bottom = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (tracksState.value) {
                TracksState.Empty -> item { Text("Track list Empty") }
                TracksState.Error -> item { Text("Track list Error") }
                is TracksState.Loaded -> {
                    (tracksState.value as TracksState.Loaded).trackList.forEach { track ->
                        item {
                            TrackCell(trackTitle = track.title)
                        }
                        item { Spacer(modifier = Modifier.size(6.dp)) }
                    }
                }

                else -> {
                    // nothing to do
                }
            }
        }
    }

}

@Composable
fun TrackCell(trackTitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(50.dp),
            painter = painterResource(android.R.drawable.star_on),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            modifier = Modifier.padding(end = 6.dp),
            text = trackTitle,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
        )
    }

}

@Composable
@Preview
fun TrackCellPreview() {
    TrackCell("Raindrops Keep Falling on my Head")
}

@Composable
@Preview
fun TrackCellLongTextPreview() {
    TrackCell("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod ex semper erat accumsan euismod. Fusce rhoncus, magna quis venenatis molestie, lorem velit pellentesque ante, eu volutpat mauris ex et orci. Quisque eget scelerisque ante")
}