package com.china.psychy.android

import com.china.psychy.android.login.LoginScreen
import com.china.psychy.auth.data.AuthRepository
import com.china.psychy.auth.data.AuthRepositoryImpl
import com.china.psychy.auth.domain.ForgotPasswordUseCase
import com.china.psychy.auth.domain.ForgotPasswordUseCaseImpl
import com.china.psychy.auth.domain.LoginUserUseCase
import com.china.psychy.auth.domain.LoginUserUseCaseImpl
import com.china.psychy.auth.domain.RegisterUserUseCase
import com.china.psychy.auth.domain.RegisterUserUseCaseImpl
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
abstract class ApplicationComponent() {
    abstract val login: LoginScreen

    @Provides
    fun provideAuthRepository(repo: AuthRepositoryImpl): AuthRepository = repo

    @Provides
    fun provideLoginUserUseCase(useCase: LoginUserUseCaseImpl): LoginUserUseCase = useCase

    @Provides
    fun provideRegisterUserUseCase(useCase: RegisterUserUseCaseImpl): RegisterUserUseCase = useCase

    @Provides
    fun provideForgotUseCase(useCase: ForgotPasswordUseCaseImpl): ForgotPasswordUseCase = useCase
}