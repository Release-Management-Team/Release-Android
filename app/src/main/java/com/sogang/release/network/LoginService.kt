package com.sogang.release.network

import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(
    val id: String,
    val password: String
)

data class LoginResponse(
    val access_token: String?,
    val refresh_token: String?
)

interface LoginService {
    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}