package com.china.psychy.auth.domain

import com.china.psychy.auth.data.AuthRepository
import com.china.psychy.auth.models.User
import me.tatarka.inject.annotations.Inject

@Inject
class LoginUserUseCaseImpl(
    private val authRepository: AuthRepository
) : LoginUserUseCase {

    override suspend fun invoke(user: User) {
        authRepository.authUser(user)
    }
}