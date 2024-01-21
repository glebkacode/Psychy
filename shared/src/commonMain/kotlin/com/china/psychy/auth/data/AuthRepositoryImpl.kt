package com.china.psychy.auth.data

import com.china.psychy.auth.data.api.RegisterUserRequest
import com.china.psychy.auth.models.User
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
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