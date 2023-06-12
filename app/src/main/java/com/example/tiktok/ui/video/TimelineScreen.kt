package com.example.tiktok.ui.video

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.tiktok.ui.components.VideoPlayer

@Composable
fun TimelineRoute(
    navigateToSignUp: () -> Unit,
) {
    TimelineScreen()
}

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnsafeOptInUsageError")
@Composable
private fun TimelineScreen() {
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
                VideoPlayer()
            }
        }
    }
}

