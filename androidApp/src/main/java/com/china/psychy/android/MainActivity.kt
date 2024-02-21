package com.china.psychy.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.china.psychy.android.core.dispatchers.CoreDispatcherImpl
import com.china.psychy.android.root.RootComponentImpl
import com.china.psychy.android.root.RootScreen
import com.china.psychy.feature.auth.data.AuthRepositoryImpl
import com.china.psychy.feature.auth.domain.ForgotPasswordUseCaseImpl
import com.china.psychy.feature.auth.domain.LoginUserUseCaseImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authRepository = AuthRepositoryImpl()
        val component = RootComponentImpl(
            componentContext = defaultComponentContext(),
            storeFactory = DefaultStoreFactory(),
            loginUserUseCase = LoginUserUseCaseImpl(authRepository),
            forgotPasswordUseCase = ForgotPasswordUseCaseImpl(authRepository),
            coreDispatcher = CoreDispatcherImpl()
        )
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootScreen(component, Modifier.fillMaxSize())
/*                    val component = InjectApplicationComponent()
                    val login = component.login
                    login()*/
/*                    val signInController = component.signInController
                    val signUpController = component.signUpController*/
                }
            }
        }
    }
}
