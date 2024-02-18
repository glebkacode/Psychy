package com.china.psychy.android.feature.auth.signup

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.china.psychy.android.core.dispatchers.CoreDispatcher
import kotlinx.coroutines.flow.mapNotNull
import me.tatarka.inject.annotations.Inject

@Inject
class SignUpController(
    storeFactory: StoreFactory,
    coreDispatcher: CoreDispatcher
) {

    private val signUpStore = SignUpStoreFactory(
        storeFactory,
        coreDispatcher.main(),
        coreDispatcher.io()
    ).create()

    val model = signUpStore.states.mapNotNull { state -> statesToModel(state) }

    fun onEmailChanged(text: String) {
        signUpStore.accept(SignUpStore.Intent.EmailChanged(text))
    }

    fun onPasswordChanged(text: String) {
        signUpStore.accept(SignUpStore.Intent.PasswordChanged(text))
    }

    fun onRegisterClicked() {
        signUpStore.accept(SignUpStore.Intent.RegistrationClicked)
    }
}