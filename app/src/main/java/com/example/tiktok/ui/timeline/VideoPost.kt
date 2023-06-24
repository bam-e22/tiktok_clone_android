package com.example.tiktok.ui.timeline

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tiktok.model.VideoItem
import com.example.tiktok.ui.components.VideoPlayer

@Composable
fun VideoPost(
    modifier: Modifier = Modifier,
    videoItem: VideoItem
) {
    Box(
        modifier = modifier
    ) {
        VideoPlayer()
        VideoInfo(videoItem)
    }
}

@Composable
private fun BoxScope.VideoInfo(
    videoItem: VideoItem
) {
    Column(
        modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(start = 20.dp, bottom = 20.dp)
    ) {
        Text(
            text = "@${videoItem.creator}",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = videoItem.title,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = videoItem.description,
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = videoItem.tags
                .joinToString(separator = " ") {
                    "#$it"
                },
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
        )
    }
}
