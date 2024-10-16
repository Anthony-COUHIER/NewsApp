package anthony.project.news.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>,
)