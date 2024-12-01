package com.sylvainvincent.myalbums.feature.tracks

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TracksScreenStateful(
    viewModel: TracksViewModel = hiltViewModel(),
    innerPaddingValues: PaddingValues
) {

    val tracksState = viewModel.trackState.collectAsState()
    val networkTracksState = viewModel.trackNetworkState.collectAsState()

    LazyColumn(Modifier.padding(innerPaddingValues)) {
        item {
            Spacer(modifier = Modifier.size(100.dp))
        }

        item {

            when (networkTracksState.value) {
                NetworkTracksState.Empty -> Text("Network Empty")
                NetworkTracksState.Error -> Text("Network Error")
                NetworkTracksState.Loading -> Text("Network Loading...")
                NetworkTracksState.NoUpdate -> Text("Network No update")
                NetworkTracksState.Success -> Text("Network Success")
            }
        }

        item {
            Spacer(modifier = Modifier.size(100.dp))
        }

        when (tracksState.value) {
            TracksState.Empty -> item { Text("Track list Empty") }
            TracksState.Error -> item { Text("Track list Error") }
            is TracksState.Loaded -> {
                (tracksState.value as TracksState.Loaded).trackList.forEach {
                    item { Text(it.title) }
                }
            }

            TracksState.Loading -> item { CircularProgressIndicator() }
        }
    }
}