package com.example.tiktok.ui.authentication.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.tiktok.R
import com.example.tiktok.ui.authentication.viewmodel.SignUpFormViewModel
import com.example.tiktok.ui.components.BasicButton
import com.example.tiktok.ui.components.TikTokTopAppBar
import com.example.tiktok.ui.components.UnderlineTextField
import com.example.tiktok.ui.theme.Black54
import com.example.tiktok.ui.utils.convertToDate
import kotlinx.coroutines.launch

@Composable
fun BirthdayRoute(
    navigateBack: () -> Unit,
    navigateToOnboarding: () -> Unit,
    viewModel: SignUpFormViewModel,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        viewModel.signUpUiEvent.collect { event ->
            when (event) {
                SignUpFormViewModel.SignUpUiEvent.SignUpSuccess -> navigateToOnboarding()
                is SignUpFormViewModel.SignUpUiEvent.SignUpFailure -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(message = event.errorMessage)
                    }
                }
            }
        }
    }

    BirthdayScreen(
        navigateBack = navigateBack,
        submit = viewModel::submitBirthday,
        signUp = viewModel::emailSignUp,
        snackbarHostState = snackbarHostState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BirthdayScreen(
    navigateBack: () -> Unit,
    submit: (birthday: String) -> Unit,
    signUp: () -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            TikTokTopAppBar(
                title = stringResource(R.string.sign_up),
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
            val datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = System.currentTimeMillis(),
            )
            var openDatePicker by remember {
                mutableStateOf(false)
            }
            val selectedDate = convertToDate(datePickerState.selectedDateMillis)
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "When's your birthday?",
                style = MaterialTheme.typography.headlineMedium
                    .copy(
                        fontWeight = FontWeight.Bold
                    ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Your birthday won't be shown publicly.",
                style = MaterialTheme.typography.bodyLarge
                    .copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Black54
                    ),
            )
            Spacer(modifier = Modifier.height(24.dp))
            UnderlineTextField(
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        openDatePicker = true
                    },
                enabled = false,
                textFieldValue = TextFieldValue(selectedDate),
                onValueChange = {},
                hintText = ""
            )
            Spacer(modifier = Modifier.height(20.dp))
            BasicButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    submit(selectedDate)
                    signUp()
                },
                enabled = true,
                text = "Next"
            )
            if (openDatePicker) {
                DatePickerDialog(
                    onDismissRequest = {
                        openDatePicker = false
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                openDatePicker = false
                            },
                        ) {
                            Text("OK")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
        }
    }
}
