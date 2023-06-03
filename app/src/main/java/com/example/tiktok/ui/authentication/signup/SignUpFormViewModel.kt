package com.example.tiktok.ui.authentication.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiktok.domain.model.SignUpModel
import com.example.tiktok.domain.usecase.EmailSignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

private const val USER_NAME_KEY = "username"
private const val EMAIL_KEY = "email"
private const val PASSWORD_KEY = "password"
private const val BIRTHDAY_KEY = "birthday"

@HiltViewModel
class SignUpFormViewModel @Inject constructor(
    private val emailSignUpUseCase: EmailSignUpUseCase,
) : ViewModel() {
    private val _signUpUiEvent = MutableSharedFlow<SignUpUiEvent>()
    val signUpUiEvent: SharedFlow<SignUpUiEvent> = _signUpUiEvent.asSharedFlow()

    private val signUpForm = mutableMapOf<String, String>()
    private val emailRegex =
        "[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?".toRegex()

    fun isEmailValid(email: String): Boolean {
        return emailRegex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length >= 8
    }

    fun submitUserName(value: String) {
        signUpForm[USER_NAME_KEY] = value
    }

    fun submitEmail(value: String) {
        signUpForm[EMAIL_KEY] = value
    }

    fun submitPassword(value: String) {
        signUpForm[PASSWORD_KEY] = value
    }

    fun submitBirthday(value: String) {
        signUpForm[BIRTHDAY_KEY] = value
    }

    fun emailSignUp() {
        val email = checkNotNull(signUpForm[EMAIL_KEY])
        val password = checkNotNull(signUpForm[PASSWORD_KEY])
        viewModelScope.launch {
            runCatching {
                emailSignUpUseCase(SignUpModel(email, password))
            }.onSuccess {
                _signUpUiEvent.emit(SignUpUiEvent.SignUpSuccess)
            }.onFailure {
                _signUpUiEvent.emit(SignUpUiEvent.SignUpFailure(it.message ?: "SignUpError"))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Timber.tag(TAG).d("onCleared")
    }

    sealed interface SignUpUiEvent {
        object SignUpSuccess : SignUpUiEvent
        class SignUpFailure(val errorMessage: String) : SignUpUiEvent
    }

    companion object {
        private const val TAG = "SignUpFormViewModel"
    }
}
