package com.example.jetpackcompose.vknews.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.jetpackcompose.vknews.domain.NewsItem
import com.example.jetpackcompose.vknews.domain.NewsStatisticItem

@Composable
fun ScreenMain() {
    val newsItem = remember { mutableStateOf(NewsItem()) }
    NewsNavigation {
        ScreenContent(
            newsItem = newsItem.value,
            onStatisticItemClickLister = { newItem ->
                val oldStatisticsItems = newsItem.value.statisticItems
                val newStatisticsItems = oldStatisticsItems.toMutableList().apply {
                    replaceAll { oldStatisticItem ->
                        if (oldStatisticItem.type == newItem.type) {
                            oldStatisticItem.copy(count = oldStatisticItem.count + 1)
                        } else {
                            oldStatisticItem
                        }
                    }
                }
                newsItem.value = newsItem.value.copy(statisticItems = newStatisticsItems)

            }
        )
    }
}

