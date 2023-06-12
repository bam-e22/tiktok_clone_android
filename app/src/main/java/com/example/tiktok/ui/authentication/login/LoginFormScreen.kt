package com.example.tiktok.ui.authentication.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.tiktok.R
import com.example.tiktok.ui.authentication.viewmodel.LoginFormViewModel
import com.example.tiktok.ui.components.BasicButton
import com.example.tiktok.ui.components.TikTokCenterAlignedTopAppBar
import com.example.tiktok.ui.components.UnderlineTextField
import kotlinx.coroutines.launch

@Composable
fun LoginFormRoute(
    navigateBack: () -> Unit,
    navigateToMain: () -> Unit,
    viewModel: LoginFormViewModel,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        viewModel.loginUiEvent.collect { event ->
            when(event) {
                LoginFormViewModel.LoginUiEvent.LoginSuccess -> navigateToMain()
                is LoginFormViewModel.LoginUiEvent.LoginFailure -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(message = event.errorMessage)
                    }
                }
            }
        }
    }

    LoginFormScreen(
        navigateBack = navigateBack,
        validateEmail = viewModel::isEmailValid,
        validatePassword = viewModel::isPasswordValid,
        signInWithEmail = viewModel::signInWithEmail,
        snackbarHostState = snackbarHostState
    )
}

@Composable
private fun LoginFormScreen(
    navigateBack: () -> Unit,
    validateEmail: (email: String) -> Boolean,
    validatePassword: (password: String) -> Boolean,
    signInWithEmail: (email: String, password: String) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            TikTokCenterAlignedTopAppBar(
                title = stringResource(R.string.log_in),
                navigateBack = navigateBack
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
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
            var emailTextFieldValue by remember {
                mutableStateOf(TextFieldValue())
            }
            var passwordTextFieldValue by remember {
                mutableStateOf(TextFieldValue())
            }

            Spacer(modifier = Modifier.height(20.dp))
            UnderlineTextField(
                textFieldValue = emailTextFieldValue,
                onValueChange = {
                    emailTextFieldValue = it
                },
                hintText = "Email",
                isValidate = validateEmail(emailTextFieldValue.text),
                errorMessage = "Email not valid"
            )
            Spacer(modifier = Modifier.height(20.dp))
            UnderlineTextField(
                textFieldValue = passwordTextFieldValue,
                onValueChange = {
                    passwordTextFieldValue = it
                },
                hintText = "Password",
                isValidate = validatePassword(passwordTextFieldValue.text),
                errorMessage = "Password not valid",
                obscureText = true
            )
            Spacer(modifier = Modifier.height(20.dp))
            BasicButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    signInWithEmail(emailTextFieldValue.text, passwordTextFieldValue.text)
                },
                enabled = validateEmail(emailTextFieldValue.text) && validatePassword(passwordTextFieldValue.text),
                text = "Next"
            )
        }
    }
}
