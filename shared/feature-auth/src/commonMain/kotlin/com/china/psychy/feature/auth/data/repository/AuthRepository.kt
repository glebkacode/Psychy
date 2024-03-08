package com.china.psychy.feature.auth.data.repository

import com.china.psychy.feature.auth.model.User

interface AuthRepository {
    suspend fun registerUser(user: User)

    suspend fun authUser(user: User)

    suspend fun resetPassword(email: String)
}