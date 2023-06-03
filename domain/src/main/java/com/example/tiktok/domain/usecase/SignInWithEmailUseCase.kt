package com.example.tiktok.domain.usecase

import com.example.tiktok.domain.model.AuthFormModel
import com.example.tiktok.domain.repository.AuthRepository
import javax.inject.Inject

class SignInWithEmailUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(authFormModel: AuthFormModel) {
        authRepository.signInWithEmail(authFormModel)
    }
}
