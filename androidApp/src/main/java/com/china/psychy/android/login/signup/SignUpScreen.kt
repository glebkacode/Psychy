package com.china.psychy.android.login.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.china.psychy.android.login.shared.Email
import com.china.psychy.android.login.shared.Password
import com.china.psychy.android.login.signup.SignUpViewModel.Event
import com.china.psychy.android.login.signup.SignUpViewModel.Event.EmailChanged
import com.china.psychy.android.login.signup.SignUpViewModel.Event.PasswordChanged
import com.china.psychy.android.login.signup.SignUpViewModel.SignUpUiState

@Composable
fun SignUpScreen(
    model: SignUpUiState,
    onEventChanged: (Event) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Email(text = model.email) { text -> onEventChanged(EmailChanged(text)) }
        Spacer(modifier = Modifier.height(16.dp))
        Password(text = model.password) { text -> onEventChanged(PasswordChanged(text)) }
        Spacer(modifier = Modifier.height(16.dp))
    }
}