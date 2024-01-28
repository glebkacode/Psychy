package com.china.psychy.android.feature.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.china.psychy.feature.auth.domain.RegisterUserUseCase
import com.china.psychy.feature.auth.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

@Inject
class SignUpViewModel(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private val stateInternal = MutableStateFlow(SignUpUiState())
    val state = stateInternal.asStateFlow()

    fun onEvent(event: Event) {
        when(event) {
            is Event.EmailChanged -> {
                stateInternal.update {
                    it.copy(email = event.text)
                }
            }
            is Event.PasswordChanged -> {
                stateInternal.update {
                    it.copy(password = event.text)
                }
            }
            Event.RegisterButtonClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    runCatching {
                        val user = User(
                            email = stateInternal.value.email,
                            password = stateInternal.value.password
                        )
                        registerUserUseCase(user)
                    }.onSuccess {

                    }.onFailure {

                    }
                }
            }
        }
    }

    data class SignUpUiState(
        val email: String = "",
        val password: String = ""
    )

    sealed interface Event {
        data class EmailChanged(val text: String) : Event
        data class PasswordChanged(val text: String) : Event
        data object RegisterButtonClicked : Event
    }
}