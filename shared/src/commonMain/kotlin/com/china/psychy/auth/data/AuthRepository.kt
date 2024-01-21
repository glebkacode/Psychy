package com.china.psychy.auth.data

import com.china.psychy.auth.models.User

interface AuthRepository {
    suspend fun registerUser(user: User)

    suspend fun authUser(user: User)

    suspend fun resetPassword(email: String)
}