package com.example.tiktok.ui.main

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
import androidx.media3.ui.PlayerView
import com.example.tiktok.ui.components.rememberVideoPlayerState

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnsafeOptInUsageError")
@Composable
fun TimelineScreen(
    navigateToSignUp: () -> Unit,
) {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
        ) {
            VerticalPager(
                pageCount = 2,
                key = { it }
            ) {
                VideoPost()
            }
        }
    }
}

@SuppressLint("UnsafeOptInUsageError")
@Composable
private fun VideoPost(
    modifier: Modifier = Modifier,
) {
    val videoPlayerState = rememberVideoPlayerState()
    AndroidView(
        modifier = modifier
            .fillMaxSize(),
        factory = { context ->
            PlayerView(context).also {
                it.resizeMode = RESIZE_MODE_ZOOM
                it.player = videoPlayerState.player
                videoPlayerState.prepare(Uri.parse("asset:///IMG_2507.MOV"))
            }
        }
    )
}
