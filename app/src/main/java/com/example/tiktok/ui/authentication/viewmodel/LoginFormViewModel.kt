package com.example.tiktok.ui.authentication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiktok.domain.delegate.InputFormValidationDelegate
import com.example.tiktok.domain.delegate.InputFormValidationDelegateImpl
import com.example.tiktok.domain.model.AuthFormModel
import com.example.tiktok.domain.usecase.SignInWithEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginFormViewModel @Inject constructor(
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
) : ViewModel(), InputFormValidationDelegate by InputFormValidationDelegateImpl() {
    private val _loginUiEvent = MutableSharedFlow<LoginUiEvent>()
    val loginUiEvent: SharedFlow<LoginUiEvent> = _loginUiEvent.asSharedFlow()

    fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {
            runCatching {
                signInWithEmailUseCase(AuthFormModel(email, password))
            }.onSuccess {
                _loginUiEvent.emit(LoginUiEvent.LoginSuccess)
            }.onFailure {
                _loginUiEvent.emit(LoginUiEvent.LoginFailure(it.message ?: "Login Error"))
            }
        }
    }

    sealed interface LoginUiEvent {
        object LoginSuccess : LoginUiEvent
        class LoginFailure(val errorMessage: String) : LoginUiEvent
    }
}
