package com.example.tiktok.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tiktok.ui.theme.ButtonBorderColor
import com.example.tiktok.ui.utils.Sizes

@Composable
fun AuthButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    painter: Painter,
    text: String,
) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.extraSmall,
        contentPadding = PaddingValues(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        border = BorderStroke(
            width = 1.dp,
            color = ButtonBorderColor,
        ),
        onClick = onClick,
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(Sizes.SmallIconSize)
        )
        Text(
            text,
            modifier = Modifier
                .weight(1f)
                .offset(-(Sizes.SmallIconSize / 2)),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
        )
    }

}
