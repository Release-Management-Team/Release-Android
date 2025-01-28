package com.sogang.release.ui.notice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sogang.release.ui.theme.AppThemeColors

@Composable
fun NoticeScreen(viewModel: NoticeViewModel = viewModel()) {
    val noticeData: List<NoticeDTO>
    noticeData = viewModel.noticeData

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppThemeColors.black1)
            .padding(30.dp)
    ) {
        LazyColumn {
            items(noticeData) { notice ->
                NoticeItem(notice)
            }
        }
    }
}