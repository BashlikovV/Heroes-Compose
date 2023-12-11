package by.bashlikovvv.heroes_compose.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import by.bashlikovvv.heroes_compose.domain.model.Hero
import coil.compose.rememberAsyncImagePainter

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    hero: Hero?
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(hero?.imageUrl),
            contentDescription = "image",
            modifier = Modifier
                .size(250.dp)
                .padding(top = 50.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = hero?.name.toString(),
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}