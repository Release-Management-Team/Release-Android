package com.sogang.release.ui.book

import BookDTO
import BookViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sogang.release.ui.activity.ActivityViewModel
//import com.sogang.release.ui.library.LibraryViewModel
import com.sogang.release.ui.theme.AppThemeColors
import com.sogang.release.ui.theme.AppTypography

@Composable
fun BookScreen(viewModel: BookViewModel = viewModel()) {
    val bookData = viewModel.booksData

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppThemeColors.black1)
            .padding(30.dp)
    ) {
        Text(
            text = "도서",
            style = AppTypography.heading3,
            color = AppThemeColors.gray1
        )

        Spacer(modifier = Modifier.height(19.dp))
        if (bookData.isEmpty()) {
            Text(
                text = "도서를 불러오는 중입니다...",
                style = AppTypography.paragraph1,
                color = AppThemeColors.gray3,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            LazyColumn {
                items(bookData) { book ->
                    BookItem(book)
                }
            }
        }
    }
}