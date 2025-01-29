package com.sogang.release.ui.activity

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sogang.release.network.RetrofitClient
import com.sogang.release.utils.UserPreferences
import kotlinx.coroutines.launch

// Data Models
data class ActivityResponse(
    val activities: List<ActivityDTO>
)

data class ActivityDTO(
    val type: String = "",
    val name: String = "",
    val description: String = "",
    val leader: String = "",
    val members: List<String> = emptyList(),
    val tags: List<String> = emptyList(),
    val state: String = "",
    val image: String = "",
    val link: String = ""
)

data class EventResponse(
    val events: List<EventDTO>
)

data class EventDTO(
    val name: String = "",
    val description: String = "",
    val start_time: String = "",
    val end_time: String = "",
    val place: String = ""
)

// UI State
sealed class ActivityTab {
    object Study : ActivityTab()
    object Event : ActivityTab()
}

// ViewModel
class ActivityViewModel : ViewModel() {
    var activityData by mutableStateOf<List<ActivityDTO>>(emptyList())
        private set
    var eventData by mutableStateOf<List<EventDTO>>(emptyList())
        private set
    var selectedTab by mutableStateOf<ActivityTab>(ActivityTab.Study)
        private set

    init {
        fetchActivityData()
        fetchEventData()
    }

    private fun fetchActivityData() {
        viewModelScope.launch {
            try {
                val accessToken = UserPreferences.getAccessToken()
                val response = RetrofitClient.activityService.getActivity("Bearer $accessToken")
                activityData = response.activities
                println("Successfully fetched activity list!")
            } catch (e: retrofit2.HttpException) {
                println("에러1: ${e.response()?.errorBody()?.string()}")
            } catch (e: java.net.UnknownHostException) {
                println("에러2")
            } catch (e: Exception) {
                println("에러3: ${e.message}")
            }
        }
    }

    private fun fetchEventData() {
        viewModelScope.launch {
            try {
                val accessToken = UserPreferences.getAccessToken()
                val response = RetrofitClient.eventService.getEvent("Bearer $accessToken")
                eventData = response.events
                println("Successfully fetched event list!")
            } catch (e: retrofit2.HttpException) {
                println("에러1: ${e.response()?.errorBody()?.string()}")
            } catch (e: java.net.UnknownHostException) {
                println("에러2")
            } catch (e: Exception) {
                println("에러3: ${e.message}")
            }
        }
    }

    fun switchTab(tab: ActivityTab) {
        selectedTab = tab
    }
}
