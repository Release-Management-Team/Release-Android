package com.sogang.release.network

import BooksResponse
import retrofit2.http.GET
import retrofit2.http.Header


interface BookService {
    @GET("/book")
    suspend fun getBook(
        @Header("Access") accessToken: String
    ): BooksResponse
}