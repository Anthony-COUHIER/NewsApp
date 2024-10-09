package anthony.project.news.ui.screen.home.section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import anthony.project.news.domain.model.Article
import anthony.project.news.ui.common.NewsAsyncImage
import anthony.project.news.ui.theme.spacing

@Composable
fun HeadlineNewsSection(
    articles: List<Article>,
    modifier: Modifier,
    onItemClicked: (article: Article) -> Unit,
) {
    Column(modifier = modifier) {
        Text(
            text = "Headline News",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(MaterialTheme.spacing.medium),
        )
        LazyRow {
            items(articles) { article ->
                NewsCard(
                    article = article,
                    onItemClicked = { onItemClicked(article) },
                )
            }
        }
    }
}

@Composable
private fun NewsCard(
    article: Article,
    onItemClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(cardWidth)
            .clickable(onClick = onItemClicked)
            .padding(start = MaterialTheme.spacing.medium),
    ) {
        NewsAsyncImage(
            imageUrl = article.urlToImage,
            contentDescription = null,
            modifier = Modifier
                .size(width = cardWidth, height = cardHeight)
                .clip(MaterialTheme.shapes.medium),
        )
        Text(
            text = article.title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 2,
        )
        Text(
            text = article.author,
            maxLines = 1,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.tertiary,
        )
    }
}

private val cardWidth: Dp = 110.dp
private val cardHeight: Dp = 90.dp