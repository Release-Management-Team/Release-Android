package com.sogang.release

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sogang.release.network.RetrofitClient
import com.sogang.release.utils.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ProfileDTO(
    val id: String = "",
    val name: String = "",
    val department: String = "",
    val phone: String = "",
    val email: String = "",
    val state: Int = 0,
    val role: Int = 0,
    val message: String = "",
    val joined_semester: String = ""
)

class MyPageViewModel : ViewModel() {
    var profileData by mutableStateOf<ProfileDTO?>(null)
        private set

    init {
        fetchProfileData()
    }

    private fun fetchProfileData() {
        viewModelScope.launch {
            try {
                val accessToken = UserPreferences.getAccessToken()
                val response = RetrofitClient.myPageService.getProfile("Bearer $accessToken")
                profileData = response
                println("Successfully fetched profile data!")
            } catch (e: retrofit2.HttpException) {
                println("에러1: ${e.response()?.errorBody()?.string()}")
            } catch (e: java.net.UnknownHostException) {
                println("에러2")
            } catch (e: Exception) {
                println("에러3: ${e.message}")
            }
        }
    }
}