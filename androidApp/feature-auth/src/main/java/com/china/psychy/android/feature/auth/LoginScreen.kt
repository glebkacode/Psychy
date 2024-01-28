package com.china.psychy.android.feature.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.china.psychy.android.feature.auth.signin.SignInScreen
import com.china.psychy.android.feature.auth.signin.SignInViewModel
import com.china.psychy.android.feature.auth.signup.SignUpScreen
import com.china.psychy.android.feature.auth.signup.SignUpViewModel
import me.tatarka.inject.annotations.Inject

typealias LoginScreen = @Composable () -> Unit

@Inject
@Composable
fun LoginScreen(
    signInViewModelFactory: () -> SignInViewModel,
    signUpViewModelFactory: () -> SignUpViewModel
) {
    val signInViewModel = viewModel { signInViewModelFactory() }
    val signUpViewModel = viewModel { signUpViewModelFactory() }

    val signInModel by signInViewModel.state.collectAsState()
    val signUpModel by signUpViewModel.state.collectAsState()
    var isSignInSelected by remember { mutableStateOf(true) }

    Switcher(
        onSignInClicked = { isSignInSelected = true },
        onSignUpClicked = { isSignInSelected = false }
    )
    Spacer(modifier = Modifier.height(32.dp))
    if (isSignInSelected) {
        SignInScreen(
            model = signInModel,
            onEventChanged = { event -> signInViewModel.onEvent(event) }
        )
    } else {
        SignUpScreen(
            model = signUpModel,
            onEventChanged = { event -> signUpViewModel.onEvent(event) }
        )
    }
}

@Composable
fun Switcher(
    onSignInClicked: () -> Unit,
    onSignUpClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {
        Tab(text = stringResource(id = R.string.signInTab)) {
            onSignInClicked()
        }
        Tab(text = stringResource(id = R.string.signUpTab)) {
            onSignUpClicked()
        }
    }
}

@Composable
fun Tab(
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(onClick = { onClick() }) {
        Text(text = text)
    }
}