package com.example.tiktok.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VideoTimelineScreen(
) {
    Scaffold {_ ->
        Text(
            "VideoTimelineScreen",
            style = MaterialTheme.typography.displayLarge
        )
    }
}
