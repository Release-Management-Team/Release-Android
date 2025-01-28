package com.sogang.release.ui.activityDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sogang.release.ui.activity.ActivityDTO
import com.sogang.release.ui.theme.AppThemeColors
import com.sogang.release.ui.theme.AppTypography

@Composable
fun ActivityDetailScreen(
    activity: ActivityDTO?,
    onJoin: () -> Unit
) {
    if (activity == null) {
        Text(
            text = "활동 정보를 불러오는 데 실패했습니다.",
            style = AppTypography.heading3,
            color = AppThemeColors.gray1,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
        return
    }

    val isRecruiting = activity.state == "모집 중"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppThemeColors.black1)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Activity Image
//            Image(
//                painter = rememberAsyncImagePainter(activity.image),
//                contentDescription = "Activity Image",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .aspectRatio(1.5f),
//                contentScale = ContentScale.Crop
//            )

            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Category and Status
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = activity.type,
                        style = AppTypography.paragraph2,
                        color = AppThemeColors.gray5
                    )

                    Text(
                        text = when (activity.state) {
                            "recruiting" -> "모집 중"
                            "before_recruit" -> "모집 예정"
                            "running" -> "모집 마감"
                            else -> ""
                        },
                        style = AppTypography.paragraph3,
                        color = AppThemeColors.black2,
                        modifier = Modifier
                            .background(
                                color = when (activity.state) {
                                    "recruiting" -> AppThemeColors.primary1
                                    "before_recruit" -> AppThemeColors.primary2
                                    "running" -> AppThemeColors.primary2
                                    else -> AppThemeColors.gray5
                                },
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Title
                Text(
                    text = activity.name,
                    style = AppTypography.heading3,
                    color = AppThemeColors.gray1
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Tags
                Text(
                    text = activity.tags.joinToString(", "),
                    style = AppTypography.paragraph1,
                    color = AppThemeColors.gray3
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Person
                Text(
                    text = "${activity.leader} 외 ${activity.members.size}명",
                    style = AppTypography.paragraph1,
                    color = AppThemeColors.gray3
                )

                Spacer(modifier = Modifier.height(48.dp))

                // Description
                Text(
                    text = activity.description,
                    style = AppTypography.paragraph1,
                    color = AppThemeColors.gray5
                )
            }
        }

        // 참여 버튼
        if (isRecruiting) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Button(
                    onClick = { onJoin() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = AppThemeColors.primary1,
                        contentColor = AppThemeColors.black2
                    )
                ) {
                    Text(
                        text = "참여하기",
                        style = AppTypography.heading4
                    )
                }
            }
        }
    }
}