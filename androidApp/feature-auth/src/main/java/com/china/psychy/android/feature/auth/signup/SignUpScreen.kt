package com.china.psychy.android.feature.auth.signup

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
import com.china.psychy.android.feature.auth.shared.Password

@Composable
fun SignUpScreen(
    component: SignUpComponent
) {
    val state by component.models.collectAsState(Model())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Email(
            text = state.email,
            isValid = true
        ) { text ->
            component.onEmailChanged(text)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Password(
            text = state.password,
            isValid = true
        ) { text ->
            component.onPasswordChanged(text)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ApplyButton(text = stringResource(id = R.string.auth_sign_up_action)) {
            component.onApplyButtonClicked()
        }
    }
}