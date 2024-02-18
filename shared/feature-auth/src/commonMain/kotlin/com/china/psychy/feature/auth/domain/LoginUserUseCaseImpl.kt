package com.china.psychy.feature.auth.domain

import com.china.psychy.feature.auth.data.AuthRepository
import com.china.psychy.feature.auth.model.User
import me.tatarka.inject.annotations.Inject

@Inject
class LoginUserUseCaseImpl(
    private val authRepository: AuthRepository
) : LoginUserUseCase {

    override suspend fun invoke(user: User) {
        authRepository.authUser(user)
    }
}