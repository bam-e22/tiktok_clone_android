package com.example.tiktok.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.tiktok.ui.theme.PrimaryCyan
import com.example.tiktok.ui.theme.PrimaryPink
import com.example.tiktok.ui.utils.Sizes

@Composable
fun TiktokAddButton(
    backgroundColor: Color,
    iconTintColor: Color,
    icon: ImageVector,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .height(70.dp)
            .drawBehind {
                val width = 40.dp.toPx()
                val height = 35.dp.toPx()
                // left cyan
                drawRoundRect(
                    topLeft = Offset(
                        size.width / 2f - (width / 2) - 6.dp.toPx(),
                        size.height / 2f - (height / 2)
                    ),
                    size = Size(
                        width = width,
                        height = height,
                    ),
                    cornerRadius = CornerRadius(6.dp.toPx()),
                    color = PrimaryCyan
                )
                // right pink
                drawRoundRect(
                    topLeft = Offset(
                        size.width / 2f - (width / 2) + 6.dp.toPx(),
                        size.height / 2f - (height / 2)
                    ),
                    size = Size(
                        width = width,
                        height = height,
                    ),
                    cornerRadius = CornerRadius(6.dp.toPx()),
                    color = PrimaryPink
                )
                // white
                drawRoundRect(
                    topLeft = Offset(
                        size.width / 2f - (width / 2),
                        size.height / 2f - (height / 2)
                    ),
                    size = Size(
                        width = width,
                        height = height,
                    ),
                    cornerRadius = CornerRadius(6.dp.toPx()),
                    color = backgroundColor
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(Sizes.SmallIconSize),
            imageVector = icon,
            tint = iconTintColor,
            contentDescription = null
        )
    }

}
