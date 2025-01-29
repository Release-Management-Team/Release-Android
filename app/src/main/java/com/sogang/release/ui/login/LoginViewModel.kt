package com.sogang.release.ui.login

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sogang.release.network.LoginRequest
import com.sogang.release.network.RetrofitClient
import com.sogang.release.utils.UserPreferences
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel : ViewModel() {
    var id by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMessage by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)
    var isLoggedIn by mutableStateOf(false)

    fun login() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                val response = RetrofitClient.loginService.login(
                    LoginRequest(
                        id = id,
                        password = password
                    )
                )

                if (response.access_token.isNullOrEmpty()) {
                    throw IllegalStateException("Access Token is null or empty")
                } else if (response.refresh_token.isNullOrEmpty()){
                    throw IllegalStateException("Refresh Token is null or empty")
                }

                // 로그인 성공 시 토큰과 비밀번호 저장
                UserPreferences.saveTokens(response.access_token, response.refresh_token)
                UserPreferences.savePassword(password = password)

                isLoggedIn = true
            } catch (e: HttpException) {
                errorMessage = "아이디 또는 비밀번호가 일치하지 않습니다."
                println(e.message())
                println("에러1: HttpException")
            } catch (e: Exception) {
                errorMessage = "네트워크 오류가 발생했습니다."
                println(e.message)
                println("에러2: Exception")
            } finally {
                isLoading = false
            }
        }
    }
}