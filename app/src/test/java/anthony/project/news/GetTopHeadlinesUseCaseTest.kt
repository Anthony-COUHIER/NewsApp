package anthony.project.news

import anthony.project.news.data.repository.ImplNewsRepository
import anthony.project.news.domain.model.Article
import anthony.project.news.domain.usecase.GetTopHeadlinesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Test

class GetTopHeadlinesUseCaseTest {
    private val newsRepository = mockk<ImplNewsRepository> {
        coEvery { getTopHeadlines(any(), any()) } returns articles
    }
    private val getTopHeadlinesUseCase = GetTopHeadlinesUseCase(newsRepository)

    @Test
    fun workProperlyTest(): Unit = runTest {
        val result = getTopHeadlinesUseCase("us", 10)
        assertEquals(articles, result)
        coVerify(exactly = 1) { newsRepository.getTopHeadlines("us", 10) }
    }

    @Test
    fun isNotValidCountryTest() = runTest {
        val result = getTopHeadlinesUseCase("test", 10)
        assertEquals(articles, result)
        coVerify { newsRepository.getTopHeadlines("us", 10) }
    }

    companion object {
        private val articles = listOf(
            Article(
                author = "latine",
                content = null,
                description = null,
                title = "utamur",
                publishedAt = LocalDate(2021, 10, 10),
                url = "https://www.google.com/#q=persius",
                urlToImage = null
            ),
            Article(
                author = "latine",
                content = null,
                description = null,
                title = "utamur",
                publishedAt = LocalDate(2021, 10, 10),
                url = "https://www.google.com/#q=persius",
                urlToImage = null
            )
        )
    }
}