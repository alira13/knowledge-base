package com.example.jetpackcompose.vknews.domain

import com.example.jetpackcompose.R

data class NewsItem(
    var groupName: String = "Какая-то группа",
    var groupAvatarResId: Int = R.drawable.ic_account,
    var publicationTime: String = "21:00",
    var publicationText: String = "Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века",
    var publicationResId: Int = R.drawable.ic_android,
    var statisticItems: List<NewsStatisticItem> = listOf(
        NewsStatisticItem(NewsStatisticItem.NewsStatisticItemType.SHARES, 23),
        NewsStatisticItem(NewsStatisticItem.NewsStatisticItemType.LIKES, 116),
        NewsStatisticItem(NewsStatisticItem.NewsStatisticItemType.VIEWS, 34),
        NewsStatisticItem(NewsStatisticItem.NewsStatisticItemType.COMMENTS, 28),
    )
)
