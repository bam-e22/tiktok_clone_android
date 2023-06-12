package com.example.tiktok.domain.usecase

import com.example.tiktok.domain.repository.AuthRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke() {
        return authRepository.signOut()
    }
}

