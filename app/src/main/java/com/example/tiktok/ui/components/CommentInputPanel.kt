package com.example.tiktok.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiktok.ui.utils.Sizes

@Composable
fun CommentInputPanel(
    creatorUid: String,
    modifier: Modifier = Modifier
) {
    val emojis = listOf(
        "\uD83D\uDE04",
        "\uD83E\uDD70",
        "\uD83D\uDE02",
        "\uD83D\uDE33",
        "\uD83D\uDE0F",
        "\uD83D\uDE05",
        "\uD83E\uDD79",
        "\uD83D\uDE14"
    )
    Surface(
        modifier = modifier,
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                emojis.forEach { emoji ->
                    TextButton(
                        modifier = Modifier.weight(1f / emojis.size),
                        onClick = { }
                    ) {
                        Text(
                            emoji,
                            fontSize = 22.sp
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircleAvatar(
                    uid = creatorUid,
                    name = "",
                    size = Sizes.MediumIconSize,
                )
                Spacer(modifier = Modifier.width(8.dp))
                CommentTextField(
                    guideText = "따뜻한 말 한마디 해주세요..."
                )
            }
        }
    }
}
