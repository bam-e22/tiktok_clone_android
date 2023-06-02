package com.example.tiktok.data.repository

import com.example.tiktok.domain.model.SignUpModel
import com.example.tiktok.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor() : AuthRepository {
    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    override suspend fun emailSignUp(signUpModel: SignUpModel) {
        val authResult = auth.createUserWithEmailAndPassword(signUpModel.email, signUpModel.password).await()
        checkNotNull(authResult.user)
    }
}
