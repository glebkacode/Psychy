package com.china.psychy.android

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.china.psychy.android.core.dispatchers.CoreDispatcher
import com.china.psychy.android.core.dispatchers.CoreDispatcherImpl
import com.china.psychy.feature.auth.data.repository.AuthRepository
import com.china.psychy.feature.auth.data.repository.AuthRepositoryImpl
import com.china.psychy.feature.auth.domain.forgotpassword.ForgotPasswordUseCase
import com.china.psychy.feature.auth.domain.forgotpassword.ForgotPasswordUseCaseImpl
import com.china.psychy.feature.auth.domain.loginuser.LoginUserUseCase
import com.china.psychy.feature.auth.domain.loginuser.LoginUserUseCaseImpl
import com.china.psychy.feature.auth.domain.registeruser.RegisterUserUseCase
import com.china.psychy.feature.auth.domain.registeruser.RegisterUserUseCaseImpl
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
abstract class ApplicationComponent {
    /*abstract val login: LoginScreen*/
/*    abstract val signInController: SignInController
    abstract val signUpController: SignUpController*/

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
}