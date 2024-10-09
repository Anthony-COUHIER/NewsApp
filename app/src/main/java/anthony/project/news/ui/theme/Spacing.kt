package anthony.project.news.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val MaterialTheme.spacing: Spacing
    @Composable @ReadOnlyComposable get() = Spacing

object Spacing {
    val none = 0.dp
    val extraSmall = 2.dp
    val tiny = 4.dp
    val small = 8.dp
    val medium = 12.dp
    val large = 16.dp

    val screenPadding: Dp = 12.dp
}