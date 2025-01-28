package com.sogang.release.ui.notice

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sogang.release.ui.theme.AppThemeColors
import com.sogang.release.ui.theme.AppTypography
import java.util.Locale
import java.util.TimeZone


@Composable
fun NoticeItem(notice: NoticeDTO) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = AppThemeColors.black2
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // 제목
            Text(
                text = notice.title,
                style = AppTypography.heading4,
                color = AppThemeColors.gray1
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 내용
            Text(
                text = notice.content,
                style = AppTypography.paragraph2,
                color = AppThemeColors.gray1
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 날짜
            Text(
                text = convertDate(notice.date),
                style = AppTypography.paragraph2,
                color = AppThemeColors.gray5
            )
        }
    }
}

fun convertDate(dateString: String): String {
    return try {
        val isoFormatter = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        isoFormatter.timeZone = TimeZone.getTimeZone("UTC")
        val date = isoFormatter.parse(dateString)

        // 한국 시간대에 맞게 포맷 변환
        val dateFormatter = java.text.SimpleDateFormat("yyyy년 MM월 dd일 HH:mm", Locale("ko", "KR"))
        dateFormatter.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        dateFormatter.format(date ?: return dateString)
    } catch (e: Exception) {
        e.printStackTrace()
        dateString
    }
}