package com.china.psychy.feature.profile.data

import com.china.psychy.feature.profile.model.User
import me.tatarka.inject.annotations.Inject

@Inject
class UserRepositoryImpl : UserRepository {

    override fun getUser(): User {
        return User(
            name = "Gleb",
            email = "glebjn@gmail.com",
            age = 29,
            balance = 1000.0
        )
    }
}