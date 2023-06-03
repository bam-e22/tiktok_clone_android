package com.example.tiktok.domain.delegate

interface InputFormValidationDelegate {
    fun isEmailValid(value: String): Boolean
    fun isPasswordValid(value: String): Boolean
}

class InputFormValidationDelegateImpl: InputFormValidationDelegate {
    private val emailRegex =
        "[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?".toRegex()

    override fun isEmailValid(value: String): Boolean {
        return emailRegex.matches(value)
    }

    override fun isPasswordValid(value: String): Boolean {
        return value.length >= 8
    }
}
