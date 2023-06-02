package com.example.tiktok.domain.repository

import com.example.tiktok.domain.model.SignUpModel

interface AuthRepository {
    suspend fun emailSignUp(signUpModel: SignUpModel)
}
