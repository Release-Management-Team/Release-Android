package com.sogang.release.network

import com.sogang.release.ui.activity.ActivityDTO
import com.sogang.release.ui.activity.ActivityResponse
import com.sogang.release.ui.activity.EventResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ActivityService {
    @GET("/activity")
    suspend fun getActivity(
        @Header("Access") accessToken: String
    ): ActivityResponse
}

interface EventService {
    @GET("/activity/event")
    suspend fun getEvent(
        @Header("Access") accessToken: String
    ): EventResponse
}