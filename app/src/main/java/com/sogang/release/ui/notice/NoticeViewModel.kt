package com.sogang.release.ui.notice

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sogang.release.network.RetrofitClient
import com.sogang.release.utils.UserPreferences
import kotlinx.coroutines.launch

data class NoticesResponse(
    val notices: List<NoticeDTO>
)

data class NoticeDTO(
    val title: String,
    val content: String,
    val date: String,
    val important: Boolean
)

class NoticeViewModel : ViewModel() {

    var noticeData by mutableStateOf<List<NoticeDTO>>(emptyList())
        private set

    init {
        fetchNoticeData()
    }

    private fun fetchNoticeData() {
        viewModelScope.launch {
            try {

                val accessToken = UserPreferences.getAccessToken()
                val response = RetrofitClient.noticeService.getNotices("Bearer $accessToken")
                noticeData = response.notices
                println("Successfully fetched notice list!")
            } catch (e: retrofit2.HttpException) {
                println("에러1: ${e.response()?.errorBody()?.string()}")
            } catch (e: java.net.UnknownHostException) {
                println("에러2")
            } catch (e: Exception) {
                println("에러3: ${e.message}")
            }
        }
    }

//    private fun getAccessToken(): String? {
//        val sharedPreferences =
//            return sharedPreferences.getString("accessToken", null)
//    }
}