package com.sogang.release

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ProfileDTO(
    val id: String,
    val name: String,
    val department: String,
    val phone: String,
    val email: String,
    val state: Int,
    val role: Int,
    val message: String?,
    val joinedSemester: String
)

class MyPageViewModel : ViewModel() {

    private val _profileData = MutableStateFlow<ProfileDTO?>(null)
    val profileData: StateFlow<ProfileDTO?> get() = _profileData

    init {
        fetchProfileData()
    }

    private fun fetchProfileData() {
        viewModelScope.launch {
            try {
                // Simulating an API call, replace with your actual API client
                val fetchedProfile = fetchProfileFromServer()
                _profileData.value = fetchedProfile
            } catch (e: Exception) {
                // Handle errors appropriately
                e.printStackTrace()
            }
        }
    }

    private suspend fun fetchProfileFromServer(): ProfileDTO {
        // Simulating an API call, replace this with your actual HTTP call logic
        return ProfileDTO(
            id = "20210001",
            name = "홍길동",
            department = "컴퓨터공학과",
            phone = "01012345678",
            email = "honggildong@example.com",
            state = 1,
            role = 2,
            message = "행복한 하루 되세요!",
            joinedSemester = "2021-1"
        )
    }
}