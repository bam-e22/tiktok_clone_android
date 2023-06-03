package com.example.tiktok.ui.authentication.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.tiktok.R
import com.example.tiktok.ui.authentication.viewmodel.SignUpFormViewModel
import com.example.tiktok.ui.components.BasicButton
import com.example.tiktok.ui.components.TikTokTopAppBar
import com.example.tiktok.ui.components.UnderlineTextField
import com.example.tiktok.ui.theme.Green500
import com.example.tiktok.ui.theme.Grey400
import com.example.tiktok.ui.utils.Sizes

@Composable
fun PasswordRoute(
    navigateBack: () -> Unit,
    navigateToBirthday: () -> Unit,
    viewModel: SignUpFormViewModel,
) {
    PasswordScreen(
        navigateBack = navigateBack,
        navigateToBirthday = navigateToBirthday,
        submit = viewModel::submitPassword,
        validatePassword = viewModel::isPasswordValid
    )
}

@Composable
private fun PasswordScreen(
    navigateBack: () -> Unit,
    navigateToBirthday: () -> Unit,
    submit: (password: String) -> Unit,
    validatePassword: (password: String) -> Boolean,
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
        var textFieldValue by remember {
            mutableStateOf(TextFieldValue())
        }
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
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Password",
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
                    submit(textFieldValue.text)
                },
                hintText = "Make it strong!",
                obscureText = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Your password must have: ",
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Icon(
                    modifier = Modifier.size(Sizes.extraSmallIconSize),
                    painter = painterResource(id = R.drawable.circle_check),
                    contentDescription = null,
                    tint = if (validatePassword(textFieldValue.text)) Green500 else Grey400
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    "8 to 20 characters",
                    style = MaterialTheme.typography.labelLarge
                        .copy(
                            fontWeight = FontWeight.Normal
                        )
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            BasicButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = navigateToBirthday,
                enabled = validatePassword(textFieldValue.text),
                text = "Next"
            )
        }
    }
}
