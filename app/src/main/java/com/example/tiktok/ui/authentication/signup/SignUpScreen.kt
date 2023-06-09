package com.example.tiktok.ui.authentication.signup

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiktok.R
import com.example.tiktok.ui.authentication.viewmodel.SignUpFormViewModel
import com.example.tiktok.ui.components.AuthButton
import com.example.tiktok.ui.theme.TiktokTheme

@Composable
fun SignUpRoute(
    navigateToLogin: () -> Unit,
    navigateToUserName: () -> Unit,
    navigateToMain: () -> Unit,
    viewModel: SignUpFormViewModel,
) {
    LaunchedEffect(Unit) {
        if (viewModel.isLoggedIn) {
            navigateToMain()
        }
    }
    if (!viewModel.isLoggedIn) {
        SignUpScreen(
            navigateToLogin = navigateToLogin,
            navigateToUserName = navigateToUserName
        )
    }
}

@Composable
private fun SignUpScreen(
    navigateToLogin: () -> Unit,
    navigateToUserName: () -> Unit,
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
                    "Already have an account?",
                    style = MaterialTheme.typography.bodyMedium
                )
                TextButton(onClick = navigateToLogin) {
                    Text(
                        "Log in",
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
                text = "Sign up for TikTok",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Create a profile, follow other accounts, make your own videos, and more.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(40.dp))
            AuthButton(
                painter = painterResource(id = R.drawable.user),
                text = "Use email & password",
                onClick = navigateToUserName
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

@Preview
@Composable
fun SignUpScreenPreview() {
    TiktokTheme {
        SignUpScreen(
            navigateToLogin = {},
            navigateToUserName = {}
        )
    }
}
