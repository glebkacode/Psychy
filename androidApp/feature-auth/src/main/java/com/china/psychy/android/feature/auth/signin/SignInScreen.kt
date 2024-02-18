package com.china.psychy.android.feature.auth.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.china.psychy.android.feature.auth.R
import com.china.psychy.android.feature.auth.shared.ApplyButton
import com.china.psychy.android.feature.auth.shared.Email
import com.china.psychy.android.feature.auth.shared.ForgotPassword
import com.china.psychy.android.feature.auth.shared.Password

@Composable
fun SignInScreen(
    controller: SignInController,
) {
    val model by controller.model.collectAsState(Model())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Email(
            text = model.email.text,
            isValid = model.email.isValid
        ) { text ->
            controller.onEmailChanged(text)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Password(
            text = model.password.text,
            isValid = model.password.isValid
        ) { text ->
            controller.onPasswordChanged(text)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ApplyButton(text = stringResource(id = R.string.auth_signin_action)) { controller.onApplyButtonClicked() }
        Spacer(modifier = Modifier.height(16.dp))
        ForgotPassword(text = stringResource(id = R.string.auth_signin_forgot_password)) { controller.onForgotPasswordClicked() }
    }
}