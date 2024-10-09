package anthony.project.news.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import anthony.project.news.data.remote.api.NewsApi
import anthony.project.news.data.remote.dto.ArticleDto
import anthony.project.news.data.remote.dto.ResponseDto
import anthony.project.news.domain.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException

class AllNewsPagingSource(
    private val newsApi: NewsApi,
    private val language: String,
) : PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> =
        withContext(Dispatchers.IO) {
            val page = params.key ?: 1
            try {
                val response: ResponseDto = newsApi.getAllNews(
                    page = page,
                    pageSize = maxPageSize,
                    language = language,
                )
                totalNewsCount += response.articles.size
                LoadResult.Page(
                    data = response.articles.map(ArticleDto::toArticle),
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (totalNewsCount >= response.totalResults) null else page + 1
                )
            } catch (e: SocketTimeoutException) {
                LoadResult.Error(Throwable("Network timeout. Please try again."))
            } catch (exception: Exception) {
                exception.printStackTrace()
                LoadResult.Error(exception)
            }
        }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private val maxPageSize = 100
}
