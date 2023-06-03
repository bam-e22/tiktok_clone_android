package com.example.tiktok.ui.authentication.onboarding

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiktok.ui.components.BasicButton

@Composable
fun TutorialRoute(
    navigateToMain: () -> Unit,
) {
    TutorialScreen(
        navigateToMain = navigateToMain
    )
}

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun TutorialScreen(
    navigateToMain: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        HorizontalPager(
            pageCount = 2
        ) { page ->
            when (page) {
                0 -> {
                    TextTutorial(
                        title = "Watch cool videos!",
                        description = "Videos are personalized for you based on what you watch, like, and share."
                    )
                }

                1 -> {
                    TextTutorial(
                        title = "Follow the rules!",
                        description = "Take care of one another! Please!",
                        onClick = navigateToMain
                    )
                }
            }
        }
    }
}

@Composable
private fun TextTutorial(
    title: String,
    description: String,
    onClick: (() -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 90.dp,
                bottom = 20.dp,
                start = 20.dp,
                end = 20.dp
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.displaySmall.copy(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 20.sp
                ),
            )
        }
        if (onClick != null) {
            BasicButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                onClick = onClick,
                text = "Enter the app!"
            )
        }
    }
}
