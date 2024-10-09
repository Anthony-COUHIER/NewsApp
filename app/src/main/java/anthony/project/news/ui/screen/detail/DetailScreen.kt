package anthony.project.news.ui.screen.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import anthony.project.news.R
import anthony.project.news.domain.model.Article
import anthony.project.news.ui.common.NewsAsyncImage
import anthony.project.news.ui.theme.spacing
import anthony.project.news.ui.utils.extension.horizontalPadding

@Composable
fun DetailRoute(
    article: Article,
    naBack: () -> Unit,
) {
    DetailScreen(article = article, navBack = naBack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailScreen(
    article: Article,
    navBack: () -> Unit,
) {
    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = navBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { uriHandler.openUri(article.url) }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_open_in_browser),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            NewsAsyncImage(
                imageUrl = article.urlToImage,
                contentDescription = null,
                modifier = Modifier
                    .height(imageHeight)
                    .fillMaxWidth()
            )
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.horizontalPadding(),
            )
            Text(
                text = article.author,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.horizontalPadding(),
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

            Text(
                text = article.content ?: article.description ?: "",
                modifier = Modifier.horizontalPadding(),
            )
        }
    }

}

private val imageHeight = 200.dp