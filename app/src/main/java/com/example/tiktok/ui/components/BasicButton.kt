package com.example.tiktok.ui.components

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun BasicButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    text: String,
) {
    FilledTonalButton(
        modifier = modifier,
        onClick = if (enabled) onClick else ({}),
        enabled = enabled,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        shape = MaterialTheme.shapes.extraSmall
    ) {
        Text(
            text,
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 16.sp
            )
        )
    }
}
