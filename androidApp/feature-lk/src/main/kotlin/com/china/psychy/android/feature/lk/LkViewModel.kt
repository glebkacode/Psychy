package com.china.psychy.android.feature.lk

import androidx.lifecycle.ViewModel
import com.china.psychy.feature.lk.domain.GetLkInfoUseCase
import me.tatarka.inject.annotations.Inject

@Inject
class LkViewModel(
    private val getLkInfoUseCase: GetLkInfoUseCase
) : ViewModel() {

    fun onEvent(event: Event) {

    }

    data class LkUiState(
        val name: String
    )

    sealed interface Event {
        data object Init : Event
    }
}