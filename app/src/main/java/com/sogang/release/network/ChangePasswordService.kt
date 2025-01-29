package com.sogang.release.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

data class ChangePasswordRequest(
    val old_password: String,
    val new_password: String
)

interface ChangePasswordService {
    @POST("/member/change-password")
    suspend fun change(
        @Header("Access") accessToken: String,
        @Body request: ChangePasswordRequest
    ): Response<Void>
}