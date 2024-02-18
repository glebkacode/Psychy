package com.china.psychy.android

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.china.psychy.android.core.dispatchers.CoreDispatcher
import com.china.psychy.android.core.dispatchers.CoreDispatcherImpl
import com.china.psychy.android.feature.auth.LoginScreen
import com.china.psychy.android.feature.auth.signin.SignInController
import com.china.psychy.android.feature.auth.signup.SignUpController
import com.china.psychy.feature.auth.data.AuthRepository
import com.china.psychy.feature.auth.data.AuthRepositoryImpl
import com.china.psychy.feature.auth.domain.ForgotPasswordUseCase
import com.china.psychy.feature.auth.domain.ForgotPasswordUseCaseImpl
import com.china.psychy.feature.auth.domain.LoginUserUseCase
import com.china.psychy.feature.auth.domain.LoginUserUseCaseImpl
import com.china.psychy.feature.auth.domain.RegisterUserUseCase
import com.china.psychy.feature.auth.domain.RegisterUserUseCaseImpl
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
abstract class ApplicationComponent {
    abstract val login: LoginScreen
    abstract val signInController: SignInController
    abstract val signUpController: SignUpController

    @Provides
    fun provideCoreDispatchers(dispatchers: CoreDispatcherImpl): CoreDispatcher = dispatchers

    @Provides
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl()

    @Provides
    fun provideLoginUserUseCase(authRepository: AuthRepository): LoginUserUseCase =
        LoginUserUseCaseImpl(authRepository)

    @Provides
    fun provideRegisterUserUseCase(useCase: RegisterUserUseCaseImpl): RegisterUserUseCase = useCase

    @Provides
    fun provideForgotUseCase(authRepository: AuthRepository): ForgotPasswordUseCase =
        ForgotPasswordUseCaseImpl(authRepository)

    @Provides
    fun provideStoreFactory(): StoreFactory {
        return DefaultStoreFactory()
        /*        return if (BuildConfig.DEBUG) {
                    LoggingStoreFactory(delegate = TimeTravelStoreFactory())
                } else {
                    DefaultStoreFactory()
                }*/
    }

    @Provides
    fun provideSignInController(
        storeFactory: StoreFactory,
        loginUserUseCase: LoginUserUseCase,
        forgotPasswordUseCase: ForgotPasswordUseCase,
        coreDispatcher: CoreDispatcher
    ): SignInController {
        return SignInController(
            storeFactory,
            loginUserUseCase,
            forgotPasswordUseCase,
            coreDispatcher
        )
    }

    @Provides
    fun provideSignUpController(
        storeFactory: StoreFactory,
        coreDispatcher: CoreDispatcher
    ): SignUpController {
        return SignUpController(storeFactory, coreDispatcher)
    }
}