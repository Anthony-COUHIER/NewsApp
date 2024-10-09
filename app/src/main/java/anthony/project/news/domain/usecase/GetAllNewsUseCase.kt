package anthony.project.news.domain.usecase

import androidx.paging.PagingData
import androidx.paging.filter
import anthony.project.news.data.DataConstant
import anthony.project.news.domain.model.Article
import anthony.project.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class GetAllNewsUseCase(
    private val newsRepository: NewsRepository,
) {
    operator fun invoke(language: String): Flow<PagingData<Article>> = newsRepository
        .getAllNews(
            language = language.takeIf { language in DataConstant.Remote.languageAvailableList }
                ?: DataConstant.Remote.defaultLanguage
        )
        .map { paging ->
            paging.filter { article: Article ->
                article.description?.isNotBlank() == true && article.content?.isNotBlank() == true
                        && article.title.isNotBlank()

            }
        }
}