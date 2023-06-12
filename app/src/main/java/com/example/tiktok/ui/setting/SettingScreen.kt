package com.example.tiktok.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tiktok.ui.components.TikTokCenterAlignedTopAppBar

@Composable
fun SettingRoute(
    navigateBack: () -> Unit,
) {
    SettingScreen(
        navigateBack = navigateBack
    )
}

@Composable
private fun SettingScreen(
    navigateBack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TikTokCenterAlignedTopAppBar(
                title = "Settings",
                navigateBack = navigateBack
            )
        }
    ) {

    }
}
