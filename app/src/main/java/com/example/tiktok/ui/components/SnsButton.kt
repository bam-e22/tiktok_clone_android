package com.example.tiktok.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tiktok.R
import com.example.tiktok.ui.utils.Sizes

@Composable
fun SnsButton(
    imageVector: ImageVector,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val painter = rememberVectorPainter(image = imageVector)
    SnsButton(
        painter = painter,
        text = text,
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun SnsButton(
    painter: Painter,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                modifier = Modifier.size(Sizes.LargeIconSize),
                painter = painter,
                tint = Color.White,
                contentDescription = stringResource(id = R.string.cd_favorite)
            )
        }
        Spacer(modifier = Modifier.height(1.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(color = Color.White),
        )
    }
}
