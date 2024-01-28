package com.china.psychy.android.session.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.china.psychy.feature.sessions.domain.GetSessionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject

@Inject
class SessionsViewModel(
    val getSessionsUseCase: GetSessionsUseCase
) : ViewModel() {

    private val stateInternal =  MutableStateFlow(SessionsUiState())
    val state = stateInternal.asStateFlow()

    fun onEvent(event: Event) {
        when(event) {
            Event.FilterClicked -> {
                stateInternal.update { it.copy(isFilterSelected = true) }
            }
            Event.Init -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val sessionsUi = getSessionsUseCase().map {
                        SessionUi(
                            id = it.id,
                            date = it.date,
                            description = ""
                        )
                    }
                    stateInternal.update {
                        it.copy(sessions = sessionsUi, isFilterSelected = false)
                    }
                }
            }
            is Event.SessionItemClicked -> TODO()
        }
    }

    data class SessionsUiState(
        val sessions: List<SessionUi> = emptyList(),
        val isFilterSelected: Boolean = false
    )

    sealed interface Event {
        data object Init : Event
        data class SessionItemClicked(val id: Long) : Event
        data object FilterClicked : Event
    }
}