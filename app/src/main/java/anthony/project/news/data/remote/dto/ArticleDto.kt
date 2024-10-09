package anthony.project.news.data.remote.dto

import anthony.project.news.data.DataConstant
import anthony.project.news.domain.model.Article
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    val source: SourceDto?,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
) {
    fun toArticle() = Article(
        author = author ?: source?.name ?:"",
        title = title,
        description = description ?: "",
        url = url,
        urlToImage = urlToImage,
        publishedAt = LocalDateTime.parse(publishedAt, DataConstant.Remote.dateTimeFormat).date,
        content = content
    )
}

@Serializable
data class SourceDto(
    val id: String?,
    val name: String
)