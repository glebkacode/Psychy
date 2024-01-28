package com.china.psychy

import com.china.psychy.auth.data.AuthRepository
import com.china.psychy.auth.domain.RegisterUserUseCase
import com.china.psychy.auth.domain.RegisterUserUseCaseImpl
import com.china.psychy.auth.model.User
import dev.mokkery.MockMode
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class RegisterUseCaseTest {
    private val authRepository: AuthRepository = mock<AuthRepository>(MockMode.autoUnit)
    private val registerUserUseCase: RegisterUserUseCase = RegisterUserUseCaseImpl(authRepository)

    @Test
    fun `register user is success`() = runTest {
        val user = User(
            email = "testemail",
            password = "testpassword"
        )

        registerUserUseCase(user)

        verifySuspend(VerifyMode.exactly(1)) {
            authRepository.registerUser(user)
        }
    }
}