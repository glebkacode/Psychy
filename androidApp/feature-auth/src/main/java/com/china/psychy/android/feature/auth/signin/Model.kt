package com.china.psychy.android.feature.auth.signin

data class Model(
    val email: EmailModel = EmailModel(),
    val password: PasswordModel = PasswordModel()
)

data class EmailModel(
    val text: String = "",
    val isValid: Boolean = true
)

data class PasswordModel(
    val text: String = "",
    val isValid: Boolean = false
)