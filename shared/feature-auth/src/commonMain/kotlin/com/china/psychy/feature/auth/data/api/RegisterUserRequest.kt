package com.china.psychy.feature.auth.data.api

data class RegisterUserRequest(
    private val email: String,
    private val password: String
)