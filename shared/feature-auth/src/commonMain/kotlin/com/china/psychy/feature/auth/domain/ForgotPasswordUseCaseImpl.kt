package com.china.psychy.feature.auth.domain

import com.china.psychy.auth.data.AuthRepository
import me.tatarka.inject.annotations.Inject

@Inject
class ForgotPasswordUseCaseImpl(
    private val authRepository: AuthRepository
) : ForgotPasswordUseCase {

    override suspend fun invoke(email: String) {
        authRepository.resetPassword(email)
    }
}