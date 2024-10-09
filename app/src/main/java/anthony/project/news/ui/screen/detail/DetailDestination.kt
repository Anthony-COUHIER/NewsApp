package anthony.project.news.ui.screen.detail

import anthony.project.news.domain.model.Article
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class DetailDestination(
    val author: String,
    val content: String?,
    val description: String?,
    val title: String,
    val publishedAt: String,
    val url: String,
    val urlToImage: String?
) {
    companion object {
        fun fromArticle(article: Article): DetailDestination = DetailDestination(
            author = article.author,
            content = article.content,
            description = article.description,
            title = article.title,
            publishedAt = article.publishedAt.toString(),
            url = article.url,
            urlToImage = article.urlToImage,
        )
    }

    fun toArticle(): Article = Article(
        author = author,
        content = content,
        description = description,
        title = title,
        publishedAt = LocalDate.parse(publishedAt),
        url = url,
        urlToImage = urlToImage,
    )
}