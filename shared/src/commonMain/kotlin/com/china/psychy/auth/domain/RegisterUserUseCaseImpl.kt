package com.china.psychy.auth.domain

import com.china.psychy.auth.data.AuthRepository
import com.china.psychy.auth.models.User
import me.tatarka.inject.annotations.Inject

@Inject
class RegisterUserUseCaseImpl(
    private val authRepository: AuthRepository
) : RegisterUserUseCase {

    override suspend fun invoke(user: User) {
        authRepository.registerUser(user)
    }
}