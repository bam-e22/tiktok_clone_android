package com.example.tiktok.data.repository

import com.example.tiktok.domain.model.AuthFormModel
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

    override suspend fun signUpWithEmail(authFormModel: AuthFormModel) {
        val authResult = auth.createUserWithEmailAndPassword(authFormModel.email, authFormModel.password).await()
        checkNotNull(authResult.user)
    }

    override suspend fun signInWithEmail(authFormModel: AuthFormModel) {
        val authResult = auth.signInWithEmailAndPassword(authFormModel.email, authFormModel.password).await()
        checkNotNull(authResult.user)
    }
}
