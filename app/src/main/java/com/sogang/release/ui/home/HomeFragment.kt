package com.sogang.release

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.sogang.release.ui.theme.Primary1
import com.sogang.release.ui.theme.Gray1
import com.sogang.release.ui.theme.Black1

import androidx.compose.ui.res.painterResource

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val noticeData by viewModel.noticeData.collectAsState(initial = "")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black1)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.release_small),
                    contentDescription = "Notifications",
                    tint = Color.White,
                    modifier = Modifier.size(80.dp)
                )

                IconButton(
                    onClick = { },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ring),
                        contentDescription = "Notifications",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "행복한 하루 🧑🏻‍💻\n오늘도 버그 없는 개발 되세요!",
                style = MaterialTheme.typography.displayMedium,
                color = Gray1,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Notice",
                style = MaterialTheme.typography.titleLarge,
                color = Primary1,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Primary1, shape = RoundedCornerShape(16.dp))
                    .height(70.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (noticeData.isNotEmpty()) noticeData else "공지사항이 없습니다.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Black1
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Weekly Events",
                style = MaterialTheme.typography.titleLarge,
                color = Gray1,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}