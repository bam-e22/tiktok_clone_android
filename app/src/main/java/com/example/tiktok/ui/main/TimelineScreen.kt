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
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerView
import com.example.tiktok.ui.components.rememberVideoPlayerState

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@UnstableApi
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
            VerticalPager(pageCount = 4) {
                val videoPlayerState = rememberVideoPlayerState()
                AndroidView(
                    modifier = Modifier
                        .fillMaxSize(),
                    factory = { context ->
                        PlayerView(context).also {
                            videoPlayerState.prepare(Uri.parse("asset:///IMG_2507.MOV"))
                            it.player = videoPlayerState.player
                        }
                    },
                    update = {

                    }
                )
            }
        }
    }
}
