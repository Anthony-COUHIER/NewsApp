package anthony.project.news.ui.screen.home

import androidx.paging.PagingData
import anthony.project.news.domain.model.Article

data class HomeUiState(
    val headlineNews: List<Article> = emptyList(),
    val articles: PagingData<Article> = PagingData.empty(),
)