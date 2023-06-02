package com.example.tiktok.domain.usecase

import com.example.tiktok.domain.model.SignUpModel
import com.example.tiktok.domain.repository.AuthRepository
import javax.inject.Inject

class EmailSignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(signUpModel: SignUpModel) {
        authRepository.emailSignUp(signUpModel)
    }
}
