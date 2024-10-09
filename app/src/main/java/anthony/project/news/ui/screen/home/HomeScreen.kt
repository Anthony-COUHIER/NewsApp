package anthony.project.news.ui.screen.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import anthony.project.news.domain.model.Article
import anthony.project.news.ui.screen.home.section.HeadlineNewsSection
import anthony.project.news.ui.screen.home.section.allNewsArticle
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoute(
    navigateToNewsDetail: (article: Article) -> Unit,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val allNewsPagingItems by rememberUpdatedState(viewModel.allNewsPagingFlow.collectAsLazyPagingItems())

    HomeScreen(
        uiState = uiState,
        allNewsPagingItems = allNewsPagingItems,
        navigateToNewsDetail = navigateToNewsDetail,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    allNewsPagingItems: LazyPagingItems<Article>,
    navigateToNewsDetail: (article: Article) -> Unit
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("News") })
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            if (uiState.headlineNews.isNotEmpty()) {
                item {
                    HeadlineNewsSection(
                        articles = uiState.headlineNews,
                        modifier = Modifier,
                        onItemClicked = navigateToNewsDetail
                    )
                }
            }

            if (allNewsPagingItems.itemCount > 0) {
                allNewsArticle(
                    articles = allNewsPagingItems,
                    onItemClicked = navigateToNewsDetail
                )
            }
        }
    }
}