package com.china.psychy.feature.auth.data

import com.china.psychy.feature.auth.model.User
import io.ktor.client.HttpClient
import me.tatarka.inject.annotations.Inject

@Inject
class AuthRepositoryImpl : AuthRepository {

    private val client = HttpClient()

    override suspend fun registerUser(user: User) {
        println("registerUser was successful")
        /*client.post("https://ktor.io/docs/registerUser") {
            contentType(ContentType.Application.Json)
            setBody(RegisterUserRequest(user.email, user.password))
        }*/
    }

    override suspend fun authUser(user: User) {
        println("authUser was successful")
/*        client.post("https://ktor.io/docs/loginUser") {
            contentType(ContentType.Application.Json)
            setBody(RegisterUserRequest(user.email, user.password))
        }*/
    }

    override suspend fun resetPassword(email: String) {
        println("resetPassword was successful")
/*        client.post("https://ktor.io/docs/resetPassword") {
            contentType(ContentType.Application.Json)
        }*/
    }
}