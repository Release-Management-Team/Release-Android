package com.sogang.release.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Visibility
//import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sogang.release.R
import com.sogang.release.ui.theme.AppThemeColors
import com.sogang.release.ui.theme.AppTypography

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppThemeColors.black1)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 로고
        Icon(
            painter = painterResource(id = R.drawable.release_big),
            contentDescription = "App Logo",
            tint = Color.Unspecified,
            modifier = Modifier
                .width(188.dp)
                .padding(top = 101.dp)
        )

        Spacer(modifier = Modifier.height(56.dp))

        // 아이디 입력
        OutlinedTextField(
            value = viewModel.id,
            onValueChange = { input ->
                if (input.all { it.isDigit() }) {
                    viewModel.id = input
                }
            },
            placeholder = {
                Text(
                    "학번",
                    style = AppTypography.paragraph1
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(AppThemeColors.black2),
            textStyle = AppTypography.paragraph1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                placeholderColor = AppThemeColors.gray5,
                textColor = AppThemeColors.gray3,
                backgroundColor = AppThemeColors.black2,
                cursorColor = AppThemeColors.gray3,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                errorBorderColor = AppThemeColors.primary4
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 비밀번호 입력
        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            placeholder = { Text("비밀번호") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(AppThemeColors.black2),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                placeholderColor = AppThemeColors.gray5,
                textColor = AppThemeColors.gray3,
                backgroundColor = AppThemeColors.black2,
                cursorColor = AppThemeColors.gray3,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                disabledBorderColor = Color.Transparent,
                errorBorderColor = AppThemeColors.primary4
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 에러 메시지
        if (viewModel.errorMessage != null) {
            Row {
                Text(
                    text = viewModel.errorMessage.toString(),
                    color = AppThemeColors.primary4,
                    style = AppTypography.paragraph3
                )

                Spacer(modifier = Modifier.weight(1f))
            }
        }

        Spacer(modifier = Modifier.height(23.dp))

        // 로그인 버튼
        Button(
            onClick = { viewModel.login() },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppThemeColors.primary1,
                contentColor = AppThemeColors.black2
            )
        ) {
            Text("로그인", style = AppTypography.heading4)
        }

        // 로그인 성공 시 화면 전환
        if (viewModel.isLoggedIn) {
            LaunchedEffect(Unit) {
                navController.navigate("main")
            }
        }
    }
}