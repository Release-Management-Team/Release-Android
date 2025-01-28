package com.sogang.release

enum class ReleaseTabBarItem(val route: String, val title: String, val normalIcon: Int, val selectedIcon: Int) {
    Home("home", "홈", R.drawable.home, R.drawable.home_primary),
    Activity("activity", "활동", R.drawable.study, R.drawable.study_primary),
    Book("book", "도서", R.drawable.book, R.drawable.book_primary),
    MyPage("my_page", "마이", R.drawable.profile, R.drawable.profile_primary)
}