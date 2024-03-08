package com.china.psychy.feature.auth.domain.registeruser

import com.china.psychy.feature.auth.data.repository.AuthRepository
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