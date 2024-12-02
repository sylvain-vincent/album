package com.sylvainvincent.myalbums.feature.tracks

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TracksScreenStateful(
    viewModel: TracksViewModel = hiltViewModel(),
    innerPaddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
) {
    val tracksState = viewModel.trackState.collectAsState()
    val networkTracksState = viewModel.trackNetworkState.collectAsState()

    val networkLoadingMessage = stringResource(R.string.track_network_synchro_loading)
    val networkErrorMessage = stringResource(R.string.track_network_synchro_error)
    val networkSuccessMessage = stringResource(R.string.track_network_synchro_success)
    val networkOfflineMessage = stringResource(R.string.track_network_offline)

    // todo implement a pull to refresh

    LaunchedEffect(networkTracksState.value) {
        when (networkTracksState.value) {
            NetworkTracksState.Loading -> {
                snackbarHostState.showSnackbar(networkLoadingMessage)
            }

            NetworkTracksState.Error -> {
                snackbarHostState.showSnackbar(networkErrorMessage)
            }

            NetworkTracksState.Success -> {
                snackbarHostState.showSnackbar(networkSuccessMessage)
            }

            NetworkTracksState.NoNetworkError -> {
                snackbarHostState.showSnackbar(networkOfflineMessage)
            }

            else -> {
                // nothing to do
            }
        }

    }

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

            TracksState.Loading -> item { CircularProgressIndicator() }
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