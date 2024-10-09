package anthony.project.news.di

import anthony.project.news.BuildConfig
import anthony.project.news.data.DataConstant
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.auth.AuthScheme
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import timber.log.Timber

@Module
@ComponentScan("anthony.project.news.data")
class DataModule {

    @Single
    fun provideJson() = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Single
    fun provideHttpClient(json: Json) = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(json, contentType = ContentType.Application.Json)
        }
        install(HttpCache)
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Timber.tag("Ktor").also { logTag ->
                        when {
                            BuildConfig.DEBUG -> logTag.d(message)
                            else -> logTag.i(message)
                        }
                    }
                }
            }
        }
        defaultRequest {
            url(DataConstant.Remote.baseUrl)
            header("Authorization", "${AuthScheme.Bearer} ${BuildConfig.API_KEY}")
        }

    }
}

@Module
@ComponentScan("anthony.project.news.ui.screen")
class ViewModelModule

@Module
@ComponentScan("anthony.project.news.domain.usecase")
class UseCaseModule

@Module(includes = [DataModule::class, ViewModelModule::class, UseCaseModule::class])
class AppModule
