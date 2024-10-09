package anthony.project.news.domain.repository

import androidx.paging.PagingData
import anthony.project.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getAllNews(language: String): Flow<PagingData<Article>>

    suspend fun getTopHeadlines(country: String, pageSize: Int): List<Article>

}