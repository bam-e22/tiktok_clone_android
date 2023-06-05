package com.example.tiktok.domain.usecase

import com.example.tiktok.domain.repository.AuthRepository
import javax.inject.Inject

class GetAuthStateUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(): Boolean {
        return authRepository.isLoggedIn()
    }
}
