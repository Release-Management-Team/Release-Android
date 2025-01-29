package com.sogang.release

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Text

import com.sogang.release.ui.theme.AppThemeColors
import com.sogang.release.ui.theme.AppTypography
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel()) {

    val noticeData by viewModel.noticeData.collectAsState(initial = "")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppThemeColors.black1)
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
                    onClick = {
                        navController.navigate("notificationsScreen")
                    },
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
                style = AppTypography.heading3,
                color = AppThemeColors.gray1,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Notice",
                style = AppTypography.heading3,
                color = AppThemeColors.primary1,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppThemeColors.primary1, shape = RoundedCornerShape(16.dp))
                    .height(70.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (noticeData.isNotEmpty()) noticeData else "",
                    style = AppTypography.paragraph1,
                    color = AppThemeColors.black1
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Weekly Events",
                style = AppTypography.heading3,
                color = AppThemeColors.gray1,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}