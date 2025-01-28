package com.sogang.release.ui.book

import BookDTO
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sogang.release.R
import com.sogang.release.ui.theme.AppThemeColors
import com.sogang.release.ui.theme.AppTypography

@Composable
fun BookItem(book: BookDTO) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = AppThemeColors.black2
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            // Book Image
//        Image(
//            painter = rememberAsyncImagePainter(
//                model = if (book.image.isEmpty()) R.drawable.release_small else book.image,
//                placeholder = painterResource(R.drawable.release_small),
//                error = painterResource(R.drawable.release_small)
//            ),
//            contentDescription = "Book Image",
//            modifier = Modifier
//                .size(96.dp)
//                .clip(RoundedCornerShape(8.dp))
//                .background(AppThemeColors.gray3),
//            contentScale = ContentScale.Crop
//        )

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 16.dp)
            ) {

                Row {
                    Spacer(modifier = Modifier.weight(1f))

                    Box(
                        modifier = Modifier
                            .background(
                                color = if (book.availability == "available") AppThemeColors.primary1 else AppThemeColors.primary2,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .width(68.dp)
                            .height(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = when (book.availability) {
                                "available" -> "대여 가능"
                                "unavailable" -> "대여 불가"
                                "rented" -> "대여 중"
                                else -> ""
                            },
                            style = AppTypography.paragraph3,
                            color = AppThemeColors.black2
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Title
                Text(
                    text = book.title,
                    style = AppTypography.heading4,
                    color = AppThemeColors.gray1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Author
                Text(
                    text = book.author,
                    style = AppTypography.paragraph2,
                    color = AppThemeColors.gray3
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Tags
                Text(
                    text = book.tags.joinToString(", "),
                    style = AppTypography.paragraph3,
                    color = AppThemeColors.gray3,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}