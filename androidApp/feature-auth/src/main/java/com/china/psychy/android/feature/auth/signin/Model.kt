package com.china.psychy.android.feature.auth.signin

data class Model(
    val email: EmailModel = EmailModel(),
    val password: PasswordModel = PasswordModel(),
    val isError: Boolean = false
)

data class EmailModel(
    val text: String = "",
    val isValid: Boolean = true
)

data class PasswordModel(
    val text: String = "",
    val isValid: Boolean = false
)