package com.china.psychy.feature.auth.domain

import com.china.psychy.feature.auth.data.AuthRepository
import com.china.psychy.feature.auth.model.User
import me.tatarka.inject.annotations.Inject

@Inject
class RegisterUserUseCaseImpl(
    private val authRepository: AuthRepository
) : RegisterUserUseCase {

    override suspend fun invoke(user: User) {
        authRepository.registerUser(user)
    }
}