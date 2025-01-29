package com.sogang.release.ui.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sogang.release.ui.theme.AppThemeColors
import com.sogang.release.ui.theme.AppTypography

@Composable
fun ActivityScreen(viewModel: ActivityViewModel = viewModel(),
                   onActivitySelected: (ActivityDTO) -> Unit) {
    val selectedTab = viewModel.selectedTab
    val activityData = viewModel.activityData
    val eventData = viewModel.eventData

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppThemeColors.black1)
            .padding(30.dp)
    ) {
        Text(
            text = "활동",
            style = AppTypography.heading3,
            color = AppThemeColors.gray1
        )

        Spacer(modifier = Modifier.height(19.dp))

        // Segmented Control
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { viewModel.switchTab(ActivityTab.Study) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selectedTab == ActivityTab.Study) AppThemeColors.primary1 else AppThemeColors.black2
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    "스터디/프로젝트",
                    color = if (selectedTab == ActivityTab.Study) AppThemeColors.black1 else AppThemeColors.gray5,
                    style = AppTypography.heading4
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { viewModel.switchTab(ActivityTab.Event) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selectedTab == ActivityTab.Event) AppThemeColors.primary1 else AppThemeColors.black2
                ),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    "이벤트",
                    color = if (selectedTab == ActivityTab.Event) AppThemeColors.black1 else AppThemeColors.gray5,
                    style = AppTypography.heading4
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            when (selectedTab) {
                is ActivityTab.Study -> {
                    items(activityData) { activity ->
                        ActivityItem(
                            activity = activity,
                            onClick = { onActivitySelected(activity) }
                        )
                    }
                }

                is ActivityTab.Event -> {
                    items(eventData) { event ->
                        EventItem(event)
                    }
                }

                else -> {}
            }
        }
    }
}