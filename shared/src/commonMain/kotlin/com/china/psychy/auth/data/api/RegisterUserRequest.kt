package com.china.psychy.auth.data.api

data class RegisterUserRequest(
    private val email: String,
    private val password: String
)