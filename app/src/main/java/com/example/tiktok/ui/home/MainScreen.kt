package com.example.tiktok.ui.home

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    Scaffold {_ ->
        Text(
            "MainScreen",
            style = MaterialTheme.typography.displayLarge
        )
    }
}
