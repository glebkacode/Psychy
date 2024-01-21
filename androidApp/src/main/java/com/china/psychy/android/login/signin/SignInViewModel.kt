package com.china.psychy.android.login.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.china.psychy.auth.domain.ForgotPasswordUseCase
import com.china.psychy.auth.domain.LoginUserUseCase
import com.china.psychy.auth.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

@Inject
class SignInViewModel(
    private val loginUserUseCase: LoginUserUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ViewModel() {

    private val stateInternal = MutableStateFlow(SignInUiState())
    val state = stateInternal.asStateFlow()

    fun onEvent(event: Event) {
        when(event) {
            is Event.EmailChanged -> {
                stateInternal.update { it.copy(email = event.text) }
            }
            is Event.PasswordChanged -> {
                stateInternal.update { it.copy(password = event.text) }
            }
            is Event.ForgotPasswordClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    forgotPasswordUseCase(stateInternal.value.email)
                }
            }
            Event.LoginClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val user = User(
                        email = stateInternal.value.email,
                        password = stateInternal.value.password
                    )
                    loginUserUseCase(user)
                }
            }
        }
    }

    data class SignInUiState(
        val email: String = "",
        val password: String = ""
    )

    sealed interface Event {
        data class EmailChanged(val text: String) : Event
        data class PasswordChanged(val text: String) : Event
        data object ForgotPasswordClicked : Event
        data object LoginClicked : Event
    }
}

