package com.example.tiktok.ui.authentication.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tiktok.ui.components.BasicButton

@Composable
fun InterestRoute(
    navigateToTutorial: () -> Unit,
) {
    InterestScreen(
        navigateToTutorial = navigateToTutorial
    )
}

@Composable
private fun InterestScreen(
    navigateToTutorial: () -> Unit,
) {
    Scaffold(
        bottomBar = {
            Surface(shadowElevation = 10.dp) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    BasicButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 16.dp,
                                start = 24.dp,
                                end = 24.dp,
                                bottom = 24.dp
                            )
                            .height(60.dp),
                        onClick = navigateToTutorial,
                        text = "Next"
                    )
                }
            }
        }
    ) { paddingValues ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(
                    top = 80.dp,
                    start = 24.dp,
                    end = 24.dp,
                    bottom = paddingValues.calculateBottomPadding() + 24.dp
                )
        ) {
            Text(
                text = "Choose your interests",
                style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Get better video recommendations",
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(40.dp))
            Interests()
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun Interests() {
    val interests = listOf(
        "Daily Life",
        "Comedy",
        "Entertainment",
        "Animals",
        "Food",
        "Beauty & Style",
        "Drama",
        "Learning",
        "Talent",
        "Sports",
        "Auto",
        "Family",
        "Fitness & Health",
        "DIY & Life Hacks",
        "Arts & Crafts",
        "Dance",
        "Outdoors",
        "Oddly Satisfying",
        "Home & Garden",
        "Daily Life",
        "Comedy",
        "Entertainment",
        "Animals",
        "Food",
        "Beauty & Style",
        "Drama",
        "Learning",
        "Talent",
        "Sports",
        "Auto",
        "Family",
        "Fitness & Health",
        "DIY & Life Hacks",
        "Arts & Crafts",
        "Dance",
        "Outdoors",
        "Oddly Satisfying",
        "Home & Garden",
    )
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        interests.forEach { text ->
            var selected by remember {
                mutableStateOf(false)
            }
            ElevatedFilterChip(
                modifier = Modifier.padding(vertical = 2.dp),
                shape = MaterialTheme.shapes.extraLarge,
                selected = selected,
                onClick = { selected = !selected },
                label = {
                    Text(
                        text,
                        modifier = Modifier.padding(vertical = 12.dp),
                    )
                },
                colors = FilterChipDefaults.elevatedFilterChipColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    labelColor = Color.Black,
                    selectedLabelColor = Color.White
                ),
                elevation = FilterChipDefaults.elevatedFilterChipElevation(
                    elevation = 3.dp
                )
            )
        }
    }
}

