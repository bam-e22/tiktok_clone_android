package com.example.tiktok.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tiktok.ui.utils.Sizes

@Composable
fun Comment(
    creatorUid: String,
    creatorName: String,
    comment: String,
    createdAt: Long,
    likes: Long,
    dislikes: Long,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 10.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            CircleAvatar(
                uid = "",
                name = "Meggin",
                modifier = Modifier.size(Sizes.MediumIconSize)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Column {
                Text(
                    creatorName,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black.copy(alpha = 0.7f))
                )
                Text(
                    comment,
                    style = MaterialTheme.typography.bodyLarge
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Text(
                            "4일",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.Black.copy(
                                    alpha = 0.4f
                                )
                            )
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            "회신",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.Black.copy(
                                    alpha = 0.7f
                                )
                            )
                        )
                    }
                    Row {
                        Text(
                            "좋아요",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.Black.copy(
                                    alpha = 0.7f
                                )
                            )
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            "싫어요",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.Black.copy(
                                    alpha = 0.7f
                                )
                            )
                        )
                    }
                }
            }
        }
    }

}
