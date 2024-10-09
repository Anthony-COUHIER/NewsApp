package anthony.project.news.ui.common

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import anthony.project.news.R
import anthony.project.news.ui.theme.spacing
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest

@Composable
fun NewsAsyncImage(
    imageUrl: String?,
    contentDescription: String?,
    modifier: Modifier,
) {
    var imageLoaded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .placeholder(R.drawable.ic_image_error)
            .error(R.drawable.ic_image_error)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .border(
                width = if (!imageLoaded) MaterialTheme.spacing.extraSmall else MaterialTheme.spacing.none,
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium
            ),
        onState = { state ->
            imageLoaded = state is AsyncImagePainter.State.Success
        }
    )
}