package com.sogang.release.ui.changePassword

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sogang.release.network.ChangePasswordRequest
import com.sogang.release.network.RetrofitClient
import com.sogang.release.utils.UserPreferences
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ChangePasswordViewModel : ViewModel() {

    var currentPassword = mutableStateOf("")
    var newPassword = mutableStateOf("")
    var confirmPassword = mutableStateOf("")

    var currentWarning = mutableStateOf<String?>(null)
    var newWarning = mutableStateOf<String?>(null)
    var confirmWarning = mutableStateOf<String?>(null)

    var isPasswordChanged = mutableStateOf(false)
    var errorMessage = mutableStateOf<String?>(null)

    fun validateAndChangePassword() {
        val savedPassword = UserPreferences.getPassword()

        if (currentPassword.value != savedPassword) {
            currentWarning.value = "비밀번호가 올바르지 않습니다."
            return
        } else {
            currentWarning.value = null
        }

        val passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d|.*[^\\w\\s]).{8,20}$".toRegex()
        if (!newPassword.value.matches(passwordRegex)) {
            newWarning.value = "영문, 숫자, 특수문자가 2종류 이상 포함된 8~20자로 설정해주세요."
            return
        } else {
            newWarning.value = null
        }

        if (newPassword.value != confirmPassword.value) {
            confirmWarning.value = "비밀번호가 일치하지 않습니다."
            return
        } else {
            confirmWarning.value = null
        }

        changePassword(currentPassword.value, newPassword.value)
    }

    private fun changePassword(oldPassword: String, newPassword: String) {
        viewModelScope.launch {
            try {
                val accessToken = UserPreferences.getAccessToken()

                accessToken?.let {
                    val response = RetrofitClient.changePasswordService.change(
                        accessToken = "Bearer $it",
                        request = ChangePasswordRequest(
                            old_password = oldPassword,
                            new_password = newPassword
                        )
                    )

                    if (response.isSuccessful) {
                        isPasswordChanged.value = true
                        UserPreferences.savePassword(newPassword)
                    } else {
                        errorMessage.value = "비밀번호 변경 실패: ${response.errorBody()?.string()}"
                    }
                } ?: run {
                    errorMessage.value = "로그인이 필요합니다."
                }

            } catch (e: HttpException) {
                errorMessage.value = "비밀번호 변경 실패: 서버 오류"
                println("에러1: HttpException ${e.message()}")
            } catch (e: Exception) {
                errorMessage.value = "네트워크 오류가 발생했습니다."
                println("에러2: Exception ${e.message}")
            }
        }
    }
}