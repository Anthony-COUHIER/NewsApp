package anthony.project.news.ui.screen.home.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import anthony.project.news.R
import anthony.project.news.domain.model.Article
import anthony.project.news.ui.common.NewsAsyncImage
import anthony.project.news.ui.theme.spacing
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames

fun LazyListScope.allNewsArticle(
    articles: LazyPagingItems<Article>,
    onItemClicked: (article: Article) -> Unit,
) {
    items(articles.itemCount) { index ->
        articles[index]?.let { article ->
            NewsRow(
                article = article,
                onItemClicked = { onItemClicked(article) },
            )
        }
    }
}


@Composable
private fun NewsRow(article: Article, onItemClicked: () -> Unit) {
    ListItem(
        headlineContent = {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 2,
            )
        },
        overlineContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.tiny),
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_schedule_icon),
                    contentDescription = null,
                    modifier = Modifier.size(MaterialTheme.spacing.medium),
                    tint = MaterialTheme.colorScheme.tertiary,
                )

                Text(
                    text = article.publishedAt.format(
                        format = LocalDate.Format {
                            monthName(MonthNames.ENGLISH_ABBREVIATED)
                            chars(" ")
                            dayOfMonth()
                            chars(", ")
                            year()
                        },
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary,
                )
            }
        },
        supportingContent = {
            Text(
                text = article.author,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.tertiary,
            )
        },
        trailingContent = {
            NewsAsyncImage(
                imageUrl = article.urlToImage,
                contentDescription = null,
                modifier = Modifier
                    .size(width = cardWidth, height = cardHeight)
                    .clip(MaterialTheme.shapes.medium),
            )
        },
        modifier = Modifier.clickable(onClick = onItemClicked),
    )
}

private val cardWidth: Dp = 110.dp
private val cardHeight: Dp = 70.dp
