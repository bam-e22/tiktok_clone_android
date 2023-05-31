package com.example.tiktok.ui.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun LoginRoute(
    navigateToSignUp: () -> Unit
) {
    LoginScreen(
        navigateToSignUp = navigateToSignUp
    )
}

@Composable
fun LoginScreen(
    navigateToSignUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        verticalArrangement = Arrangement.Center
    ) {
        TextButton(onClick = navigateToSignUp) {
            Text(
                "nav to sign up",
                style = TextStyle(
                    fontSize = 30.sp
                )
            )
        }
    }
}
