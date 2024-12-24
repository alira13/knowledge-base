import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.vknews.domain.NewsItem
import com.example.jetpackcompose.vknews.domain.NewsStatisticItem
import com.example.jetpackcompose.vknews.domain.geItemByType


@Composable
fun NewsStatistics(newsItem: NewsItem, onItemClickListener: (NewsStatisticItem) -> Unit) {

    Row(modifier = Modifier.fillMaxWidth()) {
        val views =
            newsItem.statisticItems.geItemByType(NewsStatisticItem.NewsStatisticItemType.VIEWS)
        Row(modifier = Modifier.weight(1f)) {
            MenuItem(views.count.toString(),
                views.type.resId,
                views.type.desc,
                onItemClickListener =
                {
                    onItemClickListener(views)
                })
        }
        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.SpaceBetween) {
            val likes =
                newsItem.statisticItems.geItemByType(NewsStatisticItem.NewsStatisticItemType.LIKES)
            MenuItem(
                likes.count.toString(),
                likes.type.resId,
                likes.type.desc,
                onItemClickListener = {
                    onItemClickListener(likes)
                })

            val shares =
                newsItem.statisticItems.geItemByType(NewsStatisticItem.NewsStatisticItemType.SHARES)
            MenuItem(
                shares.count.toString(),
                shares.type.resId,
                shares.type.desc,
                onItemClickListener = { onItemClickListener(shares) }
            )

            val comments =
                newsItem.statisticItems.geItemByType(NewsStatisticItem.NewsStatisticItemType.COMMENTS)
            MenuItem(
                comments.count.toString(),
                comments.type.resId,
                comments.type.desc,
                onItemClickListener = { onItemClickListener(comments) }
            )
        }
    }
}


@Composable
fun MenuItem(text: String, imageId: Int, desc: String, onItemClickListener: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
        onItemClickListener()
    }) {
        Icon(
            painter = painterResource(imageId),
            contentDescription = desc,
            tint = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text, color = MaterialTheme.colorScheme.onSecondary)
    }
}