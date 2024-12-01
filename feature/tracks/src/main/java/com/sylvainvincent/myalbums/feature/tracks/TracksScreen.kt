package com.sylvainvincent.myalbums.feature.tracks

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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

            else -> {
                // nothing to do
            }
        }

    }

    LazyColumn(
        modifier = Modifier
            .padding(innerPaddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.size(100.dp))
        }

        when (tracksState.value) {
            TracksState.Empty -> item { Text("Track list Empty") }
            TracksState.Error -> item { Text("Track list Error") }
            is TracksState.Loaded -> {
                (tracksState.value as TracksState.Loaded).trackList.forEach {
                    item { Text(it.title) }
                    item { Spacer(modifier = Modifier.size(6.dp)) }
                }
            }

            TracksState.Loading -> item { CircularProgressIndicator() }
        }
    }
}