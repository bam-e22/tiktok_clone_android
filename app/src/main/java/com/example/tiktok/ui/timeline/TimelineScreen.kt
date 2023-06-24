package com.example.tiktok.ui.timeline

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tiktok.model.VideoItem
import com.example.tiktok.ui.timeline.viewmodel.TimelineViewModel

@Composable
fun TimelineRoute(
    viewModel: TimelineViewModel,
) {
    val darkTheme = isSystemInDarkTheme()
    val view = LocalView.current
    DisposableEffect(view, darkTheme) {
        val window = (view.context as Activity).window
        window.statusBarColor = Color.Transparent.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        onDispose {
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    val videoItems by viewModel.videoItems.collectAsStateWithLifecycle()
    TimelineScreen(
        videoItems = videoItems
    )
}

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnsafeOptInUsageError")
@Composable
private fun TimelineScreen(
    videoItems: List<VideoItem>
) {
    Scaffold {
        VerticalPager(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            pageCount = videoItems.size,
            key = { it }
        ) { index ->
            VideoPost(
                videoItem = videoItems[index]
            )
        }
    }
}

