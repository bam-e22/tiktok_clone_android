package com.example.tiktok.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tiktok.ui.components.TikTokCenterAlignedTopAppBar
import com.example.tiktok.ui.setting.viewmodel.SettingViewModel

@Composable
fun SettingRoute(
    navigateBack: () -> Unit,
    navigateToAuth: () -> Unit,
    viewModel: SettingViewModel,
) {
    SettingScreen(
        navigateBack = navigateBack,
        navigateToAuth = navigateToAuth,
        signOut = viewModel::signOut
    )
}

@Composable
private fun SettingScreen(
    navigateBack: () -> Unit,
    navigateToAuth: () -> Unit,
    signOut: () -> Unit,
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
    ) { paddingValues ->
        Column(
            Modifier.padding(
                top = paddingValues.calculateTopPadding()
            )
        ) {
            ListItem(
                modifier = Modifier
                    .clickable {
                        signOut()
                        navigateToAuth()
                    },
                headlineContent = {
                    Text("SignOut")
                },
            )
        }
    }
}
