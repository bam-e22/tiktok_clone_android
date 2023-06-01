package com.example.tiktok.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun UnderlineTextField(
    textFieldValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    hintText: String
) {
    BasicTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textFieldValue,
        onValueChange = onValueChange,
        textStyle = MaterialTheme.typography.labelLarge,
        decorationBox = { innerTextField ->
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
    )
    Spacer(modifier = Modifier.height(10.dp))
    Divider(
        modifier = Modifier.fillMaxWidth(),
        color = Color.Gray
    )
}
