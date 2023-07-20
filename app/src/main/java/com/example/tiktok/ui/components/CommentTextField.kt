package com.example.tiktok.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.tiktok.R
import com.example.tiktok.ui.theme.Grey200
import com.example.tiktok.ui.utils.Sizes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentTextField(
    guideText: String,
    modifier: Modifier = Modifier
) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    BasicTextField(
        modifier = modifier,
        value = textState,
        textStyle = MaterialTheme.typography.bodyLarge,
        maxLines = 1,
        onValueChange = {
            textState = it
        },
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Grey200, CircleShape)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box {
                    if (textState.text.isEmpty()) {
                        Text(
                            guideText,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color.Black.copy(
                                    alpha = 0.7f
                                )
                            )
                        )
                    }
                    innerTextField()
                }
                Row {
                    CompositionLocalProvider(
                        LocalMinimumInteractiveComponentEnforcement provides false,
                    ) {
                        IconButton(
                            modifier = Modifier.size(Sizes.ExtraSmallIconSize),
                            onClick = { }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.at_solid),
                                tint = Color.Black,

                                contentDescription = null
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        IconButton(
                            modifier = Modifier.size(Sizes.ExtraSmallIconSize),
                            onClick = { }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.face_smile_regular),
                                tint = Color.Black,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    )
}
