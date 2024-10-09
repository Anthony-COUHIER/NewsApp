package anthony.project.news.domain.usecase

import anthony.project.news.data.DataConstant
import anthony.project.news.domain.model.Article
import anthony.project.news.domain.repository.NewsRepository
import org.koin.core.annotation.Factory
import timber.log.Timber

@Factory
class GetTopHeadlinesUseCase(
    private val newsRepository: NewsRepository,
) {
    suspend operator fun invoke(
        country: String = DataConstant.Remote.defaultCountry,
        pageSize: Int = DataConstant.Remote.defaultHeadlinesPageSize,
    ): List<Article> = try {
        newsRepository.getTopHeadlines(
            country = country
                .takeIf { country in DataConstant.Remote.countryAvailableList }
                ?: DataConstant.Remote.defaultCountry,
            pageSize = pageSize,
        )
    } catch (e: Exception) {
        Timber.e(e)
        emptyList()
    }
}