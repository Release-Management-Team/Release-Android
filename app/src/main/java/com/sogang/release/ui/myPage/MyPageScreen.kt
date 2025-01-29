package com.sogang.release

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sogang.release.ui.theme.*

@Composable
fun MyPageScreen(
    navController: NavHostController,
    viewModel: MyPageViewModel = viewModel()) {
    val profileData = viewModel.profileData

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black1)
            .padding(30.dp)
    ) {
        Column {
            Text(
                text = "마이페이지",
                style = AppTypography.heading3,
                color = AppThemeColors.gray1
            )

            Spacer(modifier = Modifier.height(30.dp))

            profileData?.let { profile ->
                ProfileSection(profile)
            }

            Spacer(modifier = Modifier.height(56.dp))

//            ChangeInfoSection(navController)
        }
    }
}

@Composable
fun ProfileSection(profile: ProfileDTO) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 30.dp)
                .background(Black2, RoundedCornerShape(16.dp))
                .border(
                    width = 1.dp,
                    color = AppThemeColors.primary2,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(24.dp)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                Column {
                    Text(
                        text = profile.name,
                        style = AppTypography.heading3,
                        color = AppThemeColors.gray1
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    profile.message?.let {
                        Text(
                            text = it,
                            style = AppTypography.paragraph1,
                            color = AppThemeColors.gray3
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Info Rows
                    InfoRow(title = "학번", content = profile.id)
                    InfoRow(title = "학과", content = profile.department)
                    InfoRow(
                        title = when (profile.state) {
                            0, 1 -> "가입기간"
                            else -> "활동기간"
                        },
                        content = when (profile.state) {
                            0, 1 -> "${profile.joined_semester} ~ NOW"
                            else -> "${profile.joined_semester} ~ END"
                        }
                    )
                    InfoRow(title = "이메일", content = profile.email)
                    InfoRow(title = "전화번호", content = formatPhoneNumber(profile.phone))
                }
            }
        }

        Row {
            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(96.dp)
                    .background(Gray3, RoundedCornerShape(48.dp))
            )
        }
    }
}

@Composable
fun InfoRow(title: String, content: String) {

    Spacer(modifier = Modifier.height(12.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = AppTypography.paragraph2,
            color = AppThemeColors.gray3
        )

        Text(
            text = content,
            style = AppTypography.paragraph2,
            color = AppThemeColors.gray2
        )
    }
}

fun formatPhoneNumber(phone: String): String {
    return phone.replace(Regex("(\\d{3})(\\d{4})(\\d{4})"), "$1-$2-$3")
}

@Composable
fun ChangeInfoSection(navController: NavHostController) {
    Column {
        Text(
            text = "정보 수정",
            style = AppTypography.heading4,
            color = AppThemeColors.gray1
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate("changePasswordScreen")
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Black2, shape = RoundedCornerShape(16.dp))
                .height(68.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Black2)
        ) {
            Text(
                text = "비밀번호 변경",
                style = AppTypography.paragraph1,
                color = AppThemeColors.gray3,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}