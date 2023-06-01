package com.example.tiktok.ui.authentication.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.tiktok.R
import com.example.tiktok.ui.components.BasicButton
import com.example.tiktok.ui.components.TikTokTopAppBar
import com.example.tiktok.ui.components.UnderlineTextField

@Composable
fun EmailRoute(
    navigateBack: () -> Unit,
    navigateToPassword: () -> Unit,
    userName: String,
    viewModel: SignUpFormViewModel,
) {
    EmailScreen(
        navigateBack = navigateBack,
        navigateToPassword = navigateToPassword,
        userName = userName,
        submit = viewModel::submitEmail,
        validateEmail = viewModel::isEmailValid
    )
}

@Composable
private fun EmailScreen(
    navigateBack: () -> Unit,
    navigateToPassword: () -> Unit,
    userName: String,
    submit: (email: String) -> Unit,
    validateEmail: (email: String) -> Boolean,
) {
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            TikTokTopAppBar(
                title = stringResource(R.string.sign_up),
                navigateBack = navigateBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    bottom = 40.dp,
                    start = 40.dp,
                    end = 40.dp,
                    top = paddingValues.calculateTopPadding() + 20.dp
                ),
        ) {
            var textFieldValue by remember {
                mutableStateOf(TextFieldValue())
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "What is your email, $userName?",
                style = MaterialTheme.typography.headlineMedium
                    .copy(
                        fontWeight = FontWeight.Bold
                    ),
            )
            Spacer(modifier = Modifier.height(30.dp))
            UnderlineTextField(
                textFieldValue = textFieldValue,
                onValueChange = {
                    textFieldValue = it
                },
                hintText = "Email",
                isValidate = validateEmail(textFieldValue.text),
                errorMessage = "Email not valid"
            )
            Spacer(modifier = Modifier.height(20.dp))
            BasicButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    submit(textFieldValue.text)
                    navigateToPassword()
                },
                enabled = textFieldValue.text.isNotEmpty() && validateEmail(textFieldValue.text),
                text = "Next"
            )
        }
    }
}
