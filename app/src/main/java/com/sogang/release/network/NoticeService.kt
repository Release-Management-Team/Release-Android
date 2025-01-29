package com.sogang.release.network

import com.sogang.release.ui.notice.NoticesResponse
import retrofit2.http.GET
import retrofit2.http.Header


interface NoticeService {
    @GET("/notice/list")
    suspend fun getNotices(
        @Header("Access") accessToken: String
    ): NoticesResponse
}