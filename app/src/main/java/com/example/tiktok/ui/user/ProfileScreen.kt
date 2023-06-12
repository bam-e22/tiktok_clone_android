package com.example.tiktok.ui.user

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tiktok.ui.components.TikTokTopAppBar

@Composable
fun ProfileRoute(
    navigateToSettings: () -> Unit,
) {
    ProfileScreen(
        navigateToSettings = navigateToSettings
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun ProfileScreen(
    navigateToSettings: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TikTokTopAppBar(
                title = "UserName",
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Edit profile"
                        )
                    }
                    IconButton(onClick = navigateToSettings) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Edit profile"
                        )
                    }
                }
            )
        },
    ) { _ ->

    }
}
