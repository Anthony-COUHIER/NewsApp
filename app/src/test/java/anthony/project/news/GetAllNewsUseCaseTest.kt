package anthony.project.news

import androidx.paging.PagingData
import androidx.paging.map
import anthony.project.news.data.DataConstant
import anthony.project.news.domain.model.Article
import anthony.project.news.domain.repository.NewsRepository
import anthony.project.news.domain.usecase.GetAllNewsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Test

class GetAllNewsUseCaseTest {
    private val newsRepository: NewsRepository = mockk()
    private val getAllNewsUseCase: GetAllNewsUseCase = GetAllNewsUseCase(newsRepository)

    @Test
    fun workProperlyTest(): TestResult = runTest {
        // Only the first article is valid
        val expectedArticles = listOf(articles[0])
        val pagingData = PagingData.from(articles)

        coEvery { newsRepository.getAllNews(any()) } returns flowOf(pagingData)

        val result = getAllNewsUseCase(DataConstant.Remote.defaultLanguage).first()

        val filteredArticles = result.collectData()
        assertEquals(expectedArticles, filteredArticles)
        coVerify { newsRepository.getAllNews("en") }
    }

    // Extension function to convert PagingData to a list for testing
    private fun PagingData<Article>.collectData(): List<Article> {
        val list = mutableListOf<Article>()
        this.map(list::add)
        return list
    }

    private val articles = listOf(
        Article(
            author = "feugait",
            content = "null",
            description = "null",
            title = "suas",
            publishedAt = LocalDate(2022, 1, 1),
            url = "https://www.google.com/#q=delectus",
            urlToImage = "null"
        ),
        Article(
            author = "feugait",
            content = "null",
            description = null,
            title = "",
            publishedAt = LocalDate(2022, 1, 1),
            url = "https://www.google.com/#q=delectus",
            urlToImage = "null"
        ),
        Article(
            author = "feugait",
            content = null,
            description = "null",
            title = "suas",
            publishedAt = LocalDate(2022, 1, 1),
            url = "https://www.google.com/#q=delectus",
            urlToImage = "null"
        ),
        Article(
            author = "feugait",
            content = "null",
            description = null,
            title = "suas",
            publishedAt = LocalDate(2022, 1, 1),
            url = "https://www.google.com/#q=delectus",
            urlToImage = "null"
        ),
    )
}