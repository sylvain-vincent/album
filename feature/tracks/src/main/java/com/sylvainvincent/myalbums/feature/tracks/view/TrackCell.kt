package com.sylvainvincent.myalbums.feature.tracks.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.network.NetworkHeaders
import coil3.network.httpHeaders
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.sylvainvincent.myalbums.core.common.getSystemUserAgent

@Composable
fun TrackCell(trackTitle: String, thumbnailUrl: String) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = getAsyncImageModelFromUrl(thumbnailUrl),
                contentDescription = "",
                placeholder = painterResource(android.R.drawable.stat_notify_sync),
                error = painterResource(android.R.drawable.stat_notify_error),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
            )
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = trackTitle,
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
            )
        }
    }
}

@Composable
private fun getAsyncImageModelFromUrl(url: String): ImageRequest {
    val headers = NetworkHeaders
        .Builder()
        .add(key = "User-Agent", value = getSystemUserAgent())
        .build()

    return ImageRequest.Builder(LocalContext.current)
        .data(url)
        .httpHeaders(headers)
        .crossfade(true)
        .build()
}

@Composable
@Preview
fun TrackCellPreview() {
    TrackCell(
        trackTitle = "Raindrops Keep Falling on my Head",
        thumbnailUrl = "http://www.test.com"
    )
}

@Composable
@Preview
fun TrackCellLongTextPreview() {
    TrackCell(
        trackTitle = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod ex semper erat accumsan euismod. Fusce rhoncus, magna quis venenatis molestie, lorem velit pellentesque ante, eu volutpat mauris ex et orci. Quisque eget scelerisque ante",
        thumbnailUrl = "http://www.test.com"
    )
}