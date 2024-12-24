package com.example.jetpackcompose.vknews.view

import NewsContent
import NewsHeader
import NewsStatistics
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.vknews.domain.NewsItem
import com.example.jetpackcompose.vknews.domain.NewsStatisticItem

@Composable
fun ScreenContent(newsItem: NewsItem, onStatisticItemClickLister: (NewsStatisticItem) -> Unit) {
    Card(
        shape = RectangleShape,
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.secondary),
        modifier = Modifier.padding(8.dp),
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            NewsHeader(newsItem)
            Spacer(modifier = Modifier.height(4.dp))
            NewsContent(newsItem)
            Spacer(modifier = Modifier.height(4.dp))
            NewsStatistics(newsItem, onItemClickListener = onStatisticItemClickLister)
        }
    }
}