package anthony.project.news

import android.app.Application
import anthony.project.news.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androix.startup.KoinStartup.onKoinStartup
import org.koin.ksp.generated.module

class NewsApp : Application() {

    init {
        @Suppress("OPT_IN_USAGE")
        onKoinStartup {
            androidLogger()
            androidContext(this@NewsApp)
            modules(AppModule().module)
        }
    }
}