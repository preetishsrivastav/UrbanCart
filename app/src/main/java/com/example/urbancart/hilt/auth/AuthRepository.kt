package com.example.urbancart.hilt.auth

import com.example.urbancart.model.network.LoginResponse
import com.example.urbancart.model.network.NetworkUser
import com.example.urbancart.model.network.post.LoginPostBody
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authService: AuthService) {

    suspend fun login(username: String, password: String): Response<LoginResponse> {
        return authService.login(LoginPostBody(username, password))
    }

    suspend fun fetchDon(): Response<NetworkUser> {
        return authService.fetchUser(userId = 4)
    }
}