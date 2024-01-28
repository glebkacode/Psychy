package com.china.psychy.android

import com.china.psychy.android.feature.auth.LoginScreen
import com.china.psychy.auth.data.AuthRepository
import com.china.psychy.feature.auth.data.AuthRepositoryImpl
import com.china.psychy.feature.auth.domain.ForgotPasswordUseCase
import com.china.psychy.feature.auth.domain.ForgotPasswordUseCaseImpl
import com.china.psychy.feature.auth.domain.LoginUserUseCase
import com.china.psychy.feature.auth.domain.LoginUserUseCaseImpl
import com.china.psychy.auth.domain.RegisterUserUseCase
import com.china.psychy.auth.domain.RegisterUserUseCaseImpl
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
abstract class ApplicationComponent() {
    abstract val login: com.china.psychy.android.feature.auth.LoginScreen

    @Provides
    fun provideAuthRepository(repo: com.china.psychy.feature.auth.data.AuthRepositoryImpl): AuthRepository = repo

    @Provides
    fun provideLoginUserUseCase(useCase: com.china.psychy.feature.auth.domain.LoginUserUseCaseImpl): com.china.psychy.feature.auth.domain.LoginUserUseCase = useCase

    @Provides
    fun provideRegisterUserUseCase(useCase: RegisterUserUseCaseImpl): RegisterUserUseCase = useCase

    @Provides
    fun provideForgotUseCase(useCase: com.china.psychy.feature.auth.domain.ForgotPasswordUseCaseImpl): com.china.psychy.feature.auth.domain.ForgotPasswordUseCase = useCase
}