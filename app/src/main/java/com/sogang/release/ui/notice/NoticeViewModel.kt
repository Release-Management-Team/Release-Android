package com.sogang.release.ui.notice

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    // 공지 데이터 리스트
    var noticeData by mutableStateOf<List<NoticeDTO>>(emptyList())
        private set

    init {
        fetchNoticeData()
    }

    fun fetchNoticeData() {
        viewModelScope.launch {
            noticeData = listOf(
                NoticeDTO(
                    title = "Scheduled Maintenance",
                    content = "We will have scheduled maintenance on 2024-06-01.We will have scheduled maintenance on 2024-06-01.We will have scheduled maintenance on 2024-06-01.We will have scheduled maintenance on 2024-06-01.We will have scheduled maintenance on 2024-06-01.We will have scheduled maintenance on 2024-06-01.",
                    date = "2024-05-25T10:00:00",
                    important = true
                ),
                NoticeDTO(
                    title = "New Features Released",
                    content = "Check out the new features in our latest update.",
                    date = "2024-05-20T09:00:00",
                    important = false
                ),
                NoticeDTO(
                    title = "Upcoming Event",
                    content = "Join us for our annual Tech Conference 2024.",
                    date = "2024-05-18T15:00:00",
                    important = true
                ),
                NoticeDTO(
                    title = "Weekly Newsletter",
                    content = "Here are the updates from last week.",
                    date = "2024-05-17T08:00:00",
                    important = false
                ),
                NoticeDTO(
                    title = "Scheduled Maintenance",
                    content = "We will have scheduled maintenance on 2024-06-01.",
                    date = "2024-05-25T10:00:00",
                    important = true
                ),
                NoticeDTO(
                    title = "New Features Released",
                    content = "Check out the new features in our latest update.",
                    date = "2024-05-20T09:00:00",
                    important = false
                ),
                NoticeDTO(
                    title = "Upcoming Event",
                    content = "Join us for our annual Tech Conference 2024.",
                    date = "2024-05-18T15:00:00",
                    important = true
                ),
                NoticeDTO(
                    title = "Weekly Newsletter",
                    content = "Here are the updates from last week.",
                    date = "2024-05-17T08:00:00",
                    important = false
                )
            )
        }
    }
}