package anthony.project.news.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDto(
    val status: String,
    val code: String,
    val message: String,
)