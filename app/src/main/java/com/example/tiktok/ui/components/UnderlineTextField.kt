package com.example.tiktok.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.tiktok.ui.theme.Grey400

@Composable
fun UnderlineTextField(
    modifier: Modifier = Modifier,
    textFieldValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    hintText: String,
    isValidate: Boolean? = null,
    errorMessage: String? = null,
    enabled: Boolean = true,
    obscureText: Boolean = false,
) {
    BasicTextField(
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        value = textFieldValue,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = MaterialTheme.typography.labelLarge,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        visualTransformation = if (obscureText) PasswordVisualTransformation() else VisualTransformation.None,
        decorationBox = { innerTextField ->
            Column(
                modifier = Modifier.drawWithContent {
                    drawContent()
                    drawLine(
                        color = if (textFieldValue.text.isEmpty() || isValidate == null || isValidate) {
                            Grey400
                        } else {
                            Color.Red
                        },
                        start = Offset(
                            x = 0f,
                            y = size.height - 1.dp.toPx()
                        ),
                        end = Offset(
                            x = size.width,
                            y = size.height - 1.dp.toPx()
                        ),
                        strokeWidth = 1.dp.toPx()
                    )
                }
            ) {
                Box {
                    if (textFieldValue.text.isEmpty()) {
                        Text(
                            text = hintText,
                            style = MaterialTheme.typography.labelLarge.copy(
                                color = Color.Gray
                            )
                        )
                    }
                    innerTextField()
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    )
    Spacer(modifier = Modifier.height(8.dp))
    if (!errorMessage.isNullOrEmpty()) {
        AnimatedVisibility(
            visible = textFieldValue.text.isNotEmpty() && isValidate == false,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                errorMessage,
                style = MaterialTheme.typography.labelMedium
                    .copy(
                        color = MaterialTheme.colorScheme.error
                    )
            )
        }
    }
}
