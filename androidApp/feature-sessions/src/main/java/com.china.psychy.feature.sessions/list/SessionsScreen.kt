package com.china.psychy.feature.sessions.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.china.psychy.android.feature.sessions.R

typealias SessionsScreen = @Composable () -> Unit

@Composable
fun SessionsScreen(
    sessionsViewModelFactory: () -> SessionsViewModel,
) {
    val sessionsViewModel = viewModel { sessionsViewModelFactory() }
    val uiState by sessionsViewModel.state.collectAsState()
    SessionsList(sessionsUi = uiState.sessions)
}

@Composable
fun SessionsList(
    sessionsUi: List<SessionUi>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            SessionsHeader()
        }
        items(sessionsUi) { sessionUi ->
            SessionItemList(sessionUi)
        }
    }
}

@Composable
fun SessionsHeader() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(id = R.string.sessionsHeader)
    )
}

@Composable
fun SessionItemList(
    sessionUi: SessionUi
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = sessionUi.date)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = sessionUi.description)
        }
    }
}