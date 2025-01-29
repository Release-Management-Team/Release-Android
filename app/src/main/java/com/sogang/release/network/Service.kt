package com.sogang.release.network
import com.sogang.release.ui.notice.NoticesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET
import retrofit2.http.Header

import com.sogang.release.BuildConfig

object RetrofitClient {
    private const val BASE_URL = BuildConfig.BASE_URL

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val loginService: LoginService by lazy {
        retrofit.create(LoginService::class.java)
    }

    val noticeService: NoticeService by lazy {
        retrofit.create(NoticeService::class.java)
    }

    val bookService: BookService by lazy {
        retrofit.create(BookService::class.java)
    }

    val activityService: ActivityService by lazy {
        retrofit.create(ActivityService::class.java)
    }

    val eventService: EventService by lazy {
        retrofit.create(EventService::class.java)
    }

    val myPageService: MyPageService by lazy {
        retrofit.create(MyPageService::class.java)
    }

    val changePasswordService: ChangePasswordService by lazy {
        retrofit.create(ChangePasswordService::class.java)
    }
}