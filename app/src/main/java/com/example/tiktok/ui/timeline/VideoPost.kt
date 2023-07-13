package com.example.tiktok.ui.timeline

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tiktok.R
import com.example.tiktok.model.VideoItem
import com.example.tiktok.ui.components.CircleAvatar
import com.example.tiktok.ui.components.VideoPlayer
import com.example.tiktok.ui.utils.Sizes

@Composable
fun VideoPost(
    videoItem: VideoItem,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        VideoPlayer()
        VideoInfo(
            videoItem = videoItem,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 20.dp)
        )
        VideoSnsButtons(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 20.dp)
        )
    }
}

@Composable
private fun VideoInfo(
    videoItem: VideoItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
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

@Composable
private fun VideoSnsButtons(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircleAvatar(
            uid = "",
            name = "",
        )
        Spacer(modifier = Modifier.height(20.dp))
        SnsButton(
            imageVector = Icons.Filled.Favorite,
            text = "0",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(20.dp))
        SnsButton(
            imageVector = Icons.Outlined.ChatBubbleOutline,
            text = "0",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(20.dp))
        SnsButton(
            imageVector = Icons.Filled.Send,
            text = "Share",
            onClick = {}
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun SnsButton(
    imageVector: ImageVector,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                modifier = Modifier.size(Sizes.LargeIconSize),
                imageVector = imageVector,
                tint = Color.White,
                contentDescription = stringResource(id = R.string.cd_favorite)
            )
        }
        Spacer(modifier = Modifier.height(1.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(color = Color.White),
        )
    }
}
