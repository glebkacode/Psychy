package com.china.psychy.android.feature.auth.signin

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.china.psychy.android.core.dispatchers.CoreDispatcher
import com.china.psychy.android.feature.auth.signin.SignInComponent.Output
import com.china.psychy.feature.auth.domain.forgotpassword.ForgotPasswordUseCase
import com.china.psychy.feature.auth.domain.loginuser.LoginUserUseCase
import kotlinx.coroutines.flow.mapNotNull

class SignInComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    loginUserUseCase: LoginUserUseCase,
    forgotPasswordUseCase: ForgotPasswordUseCase,
    coreDispatcher: CoreDispatcher,
    private val output: (Output) -> Unit
) : SignInComponent, ComponentContext by componentContext {

    private val signInStore = SignInStoreFactory(
        storeFactory,
        loginUserUseCase,
        forgotPasswordUseCase,
        coreDispatcher.main(),
        coreDispatcher.io()
    ).create()

    override val models = signInStore.states.mapNotNull { state -> statesToModel.invoke(state) }

    init {
        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY, coreDispatcher.unconfined()) {
            signInStore.labels.mapNotNull(addLabelToOutput) bindTo output
        }
    }

    override fun onEmailChanged(text: String) {
        signInStore.accept(SignInStore.Intent.EmailChanged(text))
    }

    override fun onPasswordChanged(text: String) {
        signInStore.accept(SignInStore.Intent.PasswordChanged(text))
    }

    override fun onApplyButtonClicked() {
        signInStore.accept(SignInStore.Intent.LoginClicked)
    }

    override fun onNoAccountClicked() {
        signInStore.accept(SignInStore.Intent.NoAccountClicked)
    }

    override fun onForgotPasswordClicked() {
        output(Output.OpenForgotPassword)
    }
}