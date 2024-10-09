package anthony.project.news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import anthony.project.news.data.DataConstant
import anthony.project.news.data.remote.api.NewsApi
import anthony.project.news.data.remote.dto.ArticleDto
import anthony.project.news.data.remote.paging.AllNewsPagingSource
import anthony.project.news.domain.model.Article
import anthony.project.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class ImplNewsRepository(
    private val newsApi: NewsApi,
) : NewsRepository {

    override fun getAllNews(language: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = DataConstant.Remote.defaultAllNewsPageSize),
            pagingSourceFactory = { AllNewsPagingSource(newsApi = newsApi, language = language) }
        ).flow
    }

    override suspend fun getTopHeadlines(country: String, pageSize: Int): List<Article> {
        return newsApi.getHeadlinesNews(country, pageSize).articles.map(ArticleDto::toArticle)
    }
}