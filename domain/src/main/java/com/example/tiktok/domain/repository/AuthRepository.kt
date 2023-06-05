package com.example.tiktok.domain.repository

import com.example.tiktok.domain.model.AuthFormModel

interface AuthRepository {
    suspend fun signUpWithEmail(authFormModel: AuthFormModel)
    suspend fun signInWithEmail(authFormModel: AuthFormModel)
    fun isLoggedIn(): Boolean
}
