package com.sogang.release.ui.activity

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sogang.release.R
import com.sogang.release.ui.theme.AppThemeColors
import com.sogang.release.ui.theme.AppTypography

import com.sogang.release.ui.activity.ActivityDTO
import com.sogang.release.ui.activity.EventDTO
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ActivityItem(
    activity: ActivityDTO,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = AppThemeColors.black2
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
//                painter = rememberImagePainter(activity.image),
                painter = painterResource(id = R.drawable.release_small),
                contentDescription = "Activity Image",
                modifier = Modifier
                    .size(96.dp)
            )
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = when (activity.type) {
                            "study" -> "스터디"
                            "project" -> "프로젝트"
                            else -> ""
                        },
                        style = AppTypography.paragraph3,
                        color = AppThemeColors.gray5,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    val statusText = when (activity.state) {
                        "recruiting" -> "모집 중"
                        "before_recruit" -> "모집 예정"
                        "running" -> "모집 마감"
                        else -> ""
                    }

                    val backgroundColor = when (activity.state) {
                        "recruiting" -> AppThemeColors.primary1
                        "before_recruit", "running" -> AppThemeColors.primary2
                        else -> AppThemeColors.black2
                    }

                    if (statusText.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .width(68.dp)
                                .height(24.dp)
                                .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
                                .align(Alignment.CenterVertically),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = statusText,
                                style = AppTypography.paragraph3,
                                color = AppThemeColors.black2
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(13.dp))

                Text(
                    activity.name,
                    style = AppTypography.heading4,
                    color = AppThemeColors.gray1,
                    maxLines = 2
                )

                if (activity.description != "") {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        activity.description,
                        style = AppTypography.paragraph3,
                        color = AppThemeColors.gray3,
                        maxLines = 2
                    )
                }

                if (activity.tags.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = activity.tags.joinToString("  ") { "#$it" },
                        style = AppTypography.paragraph3,
                        color = AppThemeColors.gray3,
                        maxLines = 1
                    )
                }

                if (activity.leader != "") {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        activity.leader,
                        style = AppTypography.paragraph2,
                        color = AppThemeColors.gray3
                    )
                }
            }
        }
    }
}

@Composable
fun EventItem(event: EventDTO) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = AppThemeColors.black2
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                event.name,
                style = AppTypography.heading4,
                color = AppThemeColors.gray1
            )

            if (event.place != "") {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    event.place,
                    style = AppTypography.paragraph2,
                    color = AppThemeColors.gray3
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                when {
                    event.start_time.isBlank() && event.end_time.isBlank() -> ""
                    event.start_time.isBlank() -> "~ " + formatEventTime(event.end_time)
                    event.end_time.isBlank() -> formatEventTime(event.start_time)
                    else -> "${formatEventTime(event.start_time)} ~ ${formatEventTime(event.end_time)}"
                },
                style = AppTypography.paragraph2,
                color = AppThemeColors.gray3
            )

            if (event.description != "") {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    event.description,
                    style = AppTypography.paragraph2,
                    color = AppThemeColors.gray5
                )
            }
        }
    }
}

fun formatEventTime(time: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
    val outputFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())

    val dateTime = ZonedDateTime.parse(time, formatter)

    return dateTime.format(outputFormatter)
}
