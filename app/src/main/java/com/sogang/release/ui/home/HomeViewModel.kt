package com.sogang.release

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _noticeData = MutableStateFlow("")
    val noticeData: StateFlow<String> get() = _noticeData

    init {
        loadNotices()
    }

    private fun loadNotices() {
        viewModelScope.launch {
            val fetchedNotice = fetchNoticeFromServer()
            _noticeData.value = fetchedNotice
        }
    }

    private suspend fun fetchNoticeFromServer(): String {
        // Simulating an API call
        return "이번 주 공지사항: 앱 업데이트 예정!"
    }
}