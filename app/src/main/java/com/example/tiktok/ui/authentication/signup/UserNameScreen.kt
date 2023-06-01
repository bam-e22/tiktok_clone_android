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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.tiktok.R
import com.example.tiktok.ui.components.BasicButton
import com.example.tiktok.ui.components.TikTokTopAppBar
import com.example.tiktok.ui.components.UnderlineTextField
import com.example.tiktok.ui.theme.Black54

@Composable
fun UserNameRoute(
    navigateBack: () -> Unit,
    navigateToEmail: (userName: String) -> Unit,
    viewModel: SignUpFormViewModel
) {
    UserNameScreen(
        navigateBack = navigateBack,
        navigateToEmail = navigateToEmail,
        submit = viewModel::submitUserName
    )
}

@Composable
private fun UserNameScreen(
    navigateBack: () -> Unit,
    navigateToEmail: (userName: String) -> Unit,
    submit: (userName: String) -> Unit
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
                    bottom = paddingValues.calculateBottomPadding(),
                    start = 40.dp,
                    end = 40.dp,
                    top = 80.dp
                ),
        ) {
            val textFieldValue = remember {
                mutableStateOf(TextFieldValue())
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Create user name",
                style = MaterialTheme.typography.headlineMedium
                    .copy(
                        fontWeight = FontWeight.Bold
                    ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "You can always change this later.",
                style = MaterialTheme.typography.bodyLarge
                    .copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Black54
                    ),
            )
            Spacer(modifier = Modifier.height(24.dp))
            UnderlineTextField(
                textFieldValue = textFieldValue.value,
                onValueChange = {
                    textFieldValue.value = it
                },
                hintText = "User name"
            )
            Spacer(modifier = Modifier.height(20.dp))
            BasicButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    submit(textFieldValue.value.text)
                    navigateToEmail(textFieldValue.value.text)
                },
                enabled = true,
                text = "Next"
            )
        }
    }
}
