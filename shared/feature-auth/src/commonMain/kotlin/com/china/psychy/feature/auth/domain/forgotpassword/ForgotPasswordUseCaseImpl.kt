package com.china.psychy.feature.auth.domain.forgotpassword

import com.china.psychy.feature.auth.data.repository.AuthRepository
import me.tatarka.inject.annotations.Inject

@Inject
class ForgotPasswordUseCaseImpl(
    private val authRepository: AuthRepository
) : ForgotPasswordUseCase {

    override suspend fun invoke(email: String) {
        authRepository.resetPassword(email)
    }
}