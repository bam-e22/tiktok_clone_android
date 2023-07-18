package com.example.tiktok.ui.timeline

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
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

    TimelineScreen(
        videoItems = viewModel.videoItems.collectAsStateWithLifecycle().value,
        refreshing = viewModel.refreshing.collectAsStateWithLifecycle().value,
        onRefresh = viewModel::refresh
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnsafeOptInUsageError")
@Composable
private fun TimelineScreen(
    videoItems: List<VideoItem>,
    refreshing: Boolean,
    onRefresh: () -> Unit
) {
    val refreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = onRefresh
    )
    val pagerState = rememberPagerState {
        videoItems.size
    }

    Scaffold {
        Box(
            modifier = Modifier.pullRefresh(refreshState)
        ) {
            VerticalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                key = { it }
            ) { index ->
                VideoPost(
                    videoItem = videoItems[index]
                )
            }
            PullRefreshIndicator(
                refreshing = refreshing,
                state = refreshState,
                modifier = Modifier.align(Alignment.TopCenter),
                contentColor = MaterialTheme.colorScheme.primary
            )
        }
    }
}

