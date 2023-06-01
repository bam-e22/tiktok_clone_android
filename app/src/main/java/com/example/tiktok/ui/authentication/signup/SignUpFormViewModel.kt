package com.example.tiktok.ui.authentication.signup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

private const val USER_NAME_KEY = "username"
private const val EMAIL_KEY = "email"
private const val PASSWORD_KEY = "password"
private const val BIRTHDAY_KEY = "birthday"

@HiltViewModel
class SignUpFormViewModel @Inject constructor() : ViewModel() {
    private val signUpForm = mutableMapOf<String, String>()
    private val emailRegex =
        "[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?".toRegex()

    fun isEmailValid(email: String): Boolean {
        return emailRegex.matches(email)
    }

    fun submitUserName(value: String) {
        signUpForm[USER_NAME_KEY] = value
        Timber.tag(TAG).d("signUpForm= $signUpForm")
    }

    fun submitEmail(value: String) {
        signUpForm[EMAIL_KEY] = value
        Timber.tag(TAG).d("signUpForm= $signUpForm")
    }

    fun submitPassword(value: String) {
        signUpForm[PASSWORD_KEY] = value
        Timber.tag(TAG).d("signUpForm= $signUpForm")
    }

    fun submitBirthday(value: String) {
        signUpForm[BIRTHDAY_KEY] = value
        Timber.tag(TAG).d("signUpForm= $signUpForm")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.tag(TAG).d("onCleared")
    }

    companion object {
        private const val TAG = "SignUpFormViewModel"
    }
}
