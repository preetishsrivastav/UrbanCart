package com.example.urbancart.hilt.auth

import com.example.urbancart.model.network.LoginResponse
import com.example.urbancart.model.network.NetworkUser
import com.example.urbancart.model.network.post.LoginPostBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {

    @POST("auth/login")
    suspend fun login(
        @Body postBody: LoginPostBody
    ): Response<LoginResponse>

    @GET("users/{user-id}")
    suspend fun fetchUser(
        @Path("user-id") userId: Int
    ): Response<NetworkUser>
}