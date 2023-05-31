package com.example.tiktok

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tiktok.navigation.TikTokNavGraph
import com.example.tiktok.ui.theme.TiktokTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TiktokTheme {
                TikTokNavGraph()
            }
        }
    }
}
