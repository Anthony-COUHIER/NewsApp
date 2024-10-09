package anthony.project.news.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val author: String,
    val content: String?,
    val description: String?,
    val title: String,
    val publishedAt: LocalDate,
    val url: String,
    val urlToImage: String?
)
