package com.example.tiktok.ui.main

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InboxScreen() {
    Scaffold {_ ->
        Text(
            "InboxScreen",
            style = MaterialTheme.typography.displayLarge
        )
    }
}
