package com.china.psychy.android.login.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.china.psychy.android.R
import com.china.psychy.android.login.shared.ApplyButton
import com.china.psychy.android.login.shared.Email
import com.china.psychy.android.login.shared.ForgotPassword
import com.china.psychy.android.login.shared.Password
import com.china.psychy.android.login.signin.SignInViewModel.Event
import com.china.psychy.android.login.signin.SignInViewModel.Event.EmailChanged
import com.china.psychy.android.login.signin.SignInViewModel.Event.ForgotPasswordClicked
import com.china.psychy.android.login.signin.SignInViewModel.Event.LoginClicked
import com.china.psychy.android.login.signin.SignInViewModel.Event.PasswordChanged
import com.china.psychy.android.login.signin.SignInViewModel.SignInUiState

@Composable
fun SignInScreen(
    model: SignInUiState,
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
        ApplyButton(text = stringResource(id = R.string.signInAction)) { onEventChanged(LoginClicked) }
        ForgotPassword(text = stringResource(id = R.string.signInForgotPassword)) {
            onEventChanged(ForgotPasswordClicked)
        }
    }
}