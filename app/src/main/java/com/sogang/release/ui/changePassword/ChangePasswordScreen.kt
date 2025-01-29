package com.sogang.release.ui.changePassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun ChangePasswordScreen(
    navController: NavHostController,
    viewModel: ChangePasswordViewModel = viewModel()
) {
    val isPasswordChanged by viewModel.isPasswordChanged

    LaunchedEffect(isPasswordChanged) {
        if (isPasswordChanged) {
            navController.popBackStack()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PasswordField(
            label = "현재 비밀번호",
            value = viewModel.currentPassword.value,
            onValueChange = { viewModel.currentPassword.value = it },
            warning = viewModel.currentWarning.value
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordField(
            label = "새 비밀번호",
            value = viewModel.newPassword.value,
            onValueChange = { viewModel.newPassword.value = it },
            warning = viewModel.newWarning.value
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordField(
            label = "새 비밀번호 확인",
            value = viewModel.confirmPassword.value,
            onValueChange = { viewModel.confirmPassword.value = it },
            warning = viewModel.confirmWarning.value
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.validateAndChangePassword() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        ) {
            Text("변경하기", color = Color.Black)
        }

        if (viewModel.isPasswordChanged.value) {
            Text("비밀번호가 성공적으로 변경되었습니다!", color = Color.Green)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun PasswordField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    warning: String?
) {
    Column {
        Text(label, color = Color.Gray)
        TextField(
            value = value,
            onValueChange = onValueChange,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray, RoundedCornerShape(16.dp))
                .padding(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                backgroundColor = Color.DarkGray
            )
        )
        if (warning != null) {
            Text(warning, color = Color.Red)
        }
    }
}