package com.china.psychy.android.feature.auth.signin

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.china.psychy.android.core.dispatchers.CoreDispatcher
import com.china.psychy.feature.auth.domain.ForgotPasswordUseCase
import com.china.psychy.feature.auth.domain.LoginUserUseCase
import kotlinx.coroutines.flow.mapNotNull
import me.tatarka.inject.annotations.Inject

@Inject
class SignInController(
    storeFactory: StoreFactory,
    loginUserUseCase: LoginUserUseCase,
    forgotPasswordUseCase: ForgotPasswordUseCase,
    coreDispatcher: CoreDispatcher
) {

    private val signInStore = SignInStoreFactory(
        storeFactory,
        loginUserUseCase,
        forgotPasswordUseCase,
        coreDispatcher.main(),
        coreDispatcher.io()
    ).create()

    val model = signInStore.states.mapNotNull { state -> statesToModel.invoke(state) }

    fun onEmailChanged(text: String) {
        signInStore.accept(SignInStore.Intent.EmailChanged(text))
    }

    fun onPasswordChanged(text: String) {
        signInStore.accept(SignInStore.Intent.PasswordChanged(text))
    }

    fun onApplyButtonClicked() {
        signInStore.accept(SignInStore.Intent.LoginClicked)
    }

    fun onForgotPasswordClicked() {
        signInStore.accept(SignInStore.Intent.ForgotPasswordClicked)
    }
}