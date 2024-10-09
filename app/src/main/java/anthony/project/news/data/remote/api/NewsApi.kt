package anthony.project.news.data.remote.api

import anthony.project.news.data.DataConstant
import anthony.project.news.data.remote.dto.ResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory

@Factory
class NewsApi(
    private val client: HttpClient,
) {

    suspend fun getAllNews(page: Int, pageSize: Int, language: String): ResponseDto {
        return client
            .get("/v2/everything") {
                parameter("page", page)
                parameter("pageSize", pageSize)
                parameter("sources", DataConstant.Remote.sources.joinToString(","))
                parameter("language", language)
            }
            .body<ResponseDto>()
    }

    suspend fun getHeadlinesNews(country: String, pageSize: Int): ResponseDto =
        withContext(Dispatchers.IO) {
            client
                .get("/v2/top-headlines") {
                    parameter("country", country.lowercase())
                    parameter("pageSize", pageSize)
                }
                .body<ResponseDto>()
        }

}