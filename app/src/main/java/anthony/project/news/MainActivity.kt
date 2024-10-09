package anthony.project.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import anthony.project.news.ui.navgraph.MainNavGraph
import anthony.project.news.ui.theme.NewsTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        enableEdgeToEdge()
        setContent {
            NewsTheme {
                MainNavGraph()
            }
        }
    }
}
