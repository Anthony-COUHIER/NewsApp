package anthony.project.news.ui.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import anthony.project.news.domain.model.Article
import anthony.project.news.ui.screen.detail.DetailDestination
import anthony.project.news.ui.screen.detail.DetailRoute
import anthony.project.news.ui.screen.home.HomeDestination
import anthony.project.news.ui.screen.home.HomeRoute

@Composable
fun MainNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeDestination) {

        composable<HomeDestination> {
            HomeRoute(
                navigateToNewsDetail = { article: Article ->
                    navController.navigate(
                        DetailDestination.fromArticle(article)
                    )
                },
            )
        }

        composable<DetailDestination> {
            val article = it.toRoute<DetailDestination>().toArticle()
            DetailRoute(
                article = article,
                naBack = { navController.navigateUp() }
            )
        }

    }
}