package com.sogang.release

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sogang.release.ui.theme.*

@Composable
fun MyPageScreen(viewModel: MyPageViewModel = viewModel()) {
    val profileData by viewModel.profileData.collectAsState()

    LaunchedEffect(Unit) {
//        viewModel.fetchProfileData()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black1)
            .padding(16.dp)
    ) {
        Column {
            // Title
            Text(
                text = "마이페이지",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Gray1
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Profile Section
            profileData?.let { profile ->
                ProfileSection(profile)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Change Info Section
            ChangeInfoSection()
        }
    }
}

@Composable
fun ProfileSection(profile: ProfileDTO) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Black2, RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(96.dp)
                .background(Gray3, RoundedCornerShape(48.dp))
        ) {
            // Placeholder for profile image
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = profile.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Gray1
            )
            profile.message?.let {
                Text(
                    text = it,
                    fontSize = 14.sp,
                    color = Gray3
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Info Rows
    InfoRow(title = "학번", content = profile.id)
    InfoRow(title = "학과", content = profile.department)
    InfoRow(title = "가입기간", content = "${profile.joinedSemester} ~ ${if (profile.state == 0) "NOW" else "END"}")
    InfoRow(title = "이메일", content = profile.email)
    InfoRow(title = "전화번호", content = formatPhoneNumber(profile.phone))
}

@Composable
fun InfoRow(title: String, content: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            color = Gray3
        )
        Text(
            text = content,
            fontSize = 14.sp,
            color = Gray5
        )
    }
}

fun formatPhoneNumber(phone: String): String {
    return phone.replace(Regex("(\\d{3})(\\d{4})(\\d{4})"), "$1-$2-$3")
}

@Composable
fun ChangeInfoSection() {
    Column {
        Text(
            text = "정보 수정",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Gray1
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .background(Black2, shape = RoundedCornerShape(16.dp))
                .height(68.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Black2)
        ) {
            Text(
                text = "비밀번호 변경",
                style = MaterialTheme.typography.bodyLarge,
                color = Gray1,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}