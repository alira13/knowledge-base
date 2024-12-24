package com.example.jetpackcompose.vknews.domain

import com.example.jetpackcompose.R

data class NewsStatisticItem(var type: NewsStatisticItemType, var count: Int) {
    enum class NewsStatisticItemType(val resId: Int, val desc: String) {
        VIEWS(R.drawable.ic_instagram, "views desc"),
        SHARES(R.drawable.ic_instagram, "shares desc"),
        COMMENTS(R.drawable.ic_instagram, "comments desc"),
        LIKES(R.drawable.ic_instagram, "likes desc"),
    }
}

fun List<NewsStatisticItem>.geItemByType(type: NewsStatisticItem.NewsStatisticItemType): NewsStatisticItem {
    return this.find { it -> it.type == type }
        ?: throw IllegalStateException("NewsStatisticItem for type $type not found")
}

