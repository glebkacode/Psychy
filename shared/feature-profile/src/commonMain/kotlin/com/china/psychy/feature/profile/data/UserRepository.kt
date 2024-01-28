package com.china.psychy.feature.profile.data

import com.china.psychy.feature.profile.model.User

interface UserRepository {
    fun getUser(): User
}