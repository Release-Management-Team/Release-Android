package com.sogang.release.ui.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sogang.release.R
import com.sogang.release.ui.theme.AppThemeColors
import com.sogang.release.ui.theme.AppTypography

import com.sogang.release.ui.activity.ActivityDTO
import com.sogang.release.ui.activity.EventDTO

@Composable
fun ActivityItem(activity: ActivityDTO,
                 onClick: () -> Unit) {
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
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
//                painter = rememberImagePainter(activity.image),
                painter = painterResource(id = R.drawable.release_small),
                contentDescription = "Activity Image",
                modifier = Modifier
                    .size(96.dp)
            )
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Row {
                    Text(
                        activity.type,
                        style = AppTypography.paragraph3,
                        color = AppThemeColors.gray5
                    )
                    Text(
                        activity.state,
                        style = AppTypography.paragraph3,
                        color = AppThemeColors.black2
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    activity.name,
                    style = AppTypography.heading4,
                    color = AppThemeColors.gray1
                )
                Text(
                    activity.description,
                    style = AppTypography.paragraph3,
                    color = AppThemeColors.gray3
                )

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

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                event.startTime + " ~ " + event.endTime,
                style = AppTypography.paragraph2,
                color = AppThemeColors.gray3
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                event.place,
                style = AppTypography.paragraph2,
                color = AppThemeColors.gray3
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                event.description,
                style = AppTypography.paragraph2,
                color = AppThemeColors.gray5
            )
        }
    }
}
