package com.sogang.release.network

import com.sogang.release.ProfileDTO
import retrofit2.http.GET
import retrofit2.http.Header

interface MyPageService {
    @GET("/member/profile")
    suspend fun getProfile(
        @Header("Access") accessToken: String
    ): ProfileDTO
}