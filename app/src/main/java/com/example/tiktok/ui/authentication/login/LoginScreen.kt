package com.example.tiktok.ui.authentication.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tiktok.R
import com.example.tiktok.ui.components.AuthButton

@Composable
fun LoginRoute(
    navigateToSignUp: () -> Unit,
    navigateToLoginForm: () -> Unit,
) {
    LoginScreen(
        navigateToSignUp = navigateToSignUp,
        navigateToLoginForm = navigateToLoginForm
    )
}

@Composable
fun LoginScreen(
    navigateToSignUp: () -> Unit,
    navigateToLoginForm: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background),
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(top = 15.dp, bottom = 25.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "Don't have an account?",
                    style = MaterialTheme.typography.bodyMedium
                )
                TextButton(onClick = navigateToSignUp) {
                    Text(
                        "Sign up",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Red)
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    bottom = paddingValues.calculateBottomPadding(),
                    start = 40.dp,
                    end = 40.dp,
                    top = 80.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Log in to TikTok",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Manage your account, check notifications, comment on videos, and more.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(40.dp))
            AuthButton(
                painter = painterResource(id = R.drawable.user),
                text = "Use email & password",
                onClick = navigateToLoginForm
            )
            Spacer(modifier = Modifier.height(16.dp))
            AuthButton(
                painter = painterResource(id = R.drawable.github),
                text = "Continue with Github",
                onClick = { }
            )

        }
    }

}
