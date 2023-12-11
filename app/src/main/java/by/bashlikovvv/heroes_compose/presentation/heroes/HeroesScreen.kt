package by.bashlikovvv.heroes_compose.presentation.heroes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import by.bashlikovvv.heroes_compose.domain.model.Hero
import by.bashlikovvv.heroes_compose.presentation.viewModel.HeroesFragmentViewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun HeroesScreen(
    modifier: Modifier = Modifier,
    viewModel: HeroesFragmentViewModel = hiltViewModel(),
    onHeroClicked: (Hero) -> Unit,
) {
    val heroes by viewModel.heroes.collectAsState()
    val lazyListState = rememberLazyListState()

    LazyColumn(modifier = modifier, state = lazyListState) {
        items(items = heroes, key = { it.id }) {
            if (it is Hero) {
                HeroesListItem(hero = it, onHeroClicked = onHeroClicked)
            } else {
                ProgressListItem()
            }
        }
    }

    if (lazyListState.isScrolledToTheEnd()) {
        viewModel.incrementPage()
    }
}

private fun LazyListState.isScrolledToTheEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

@Composable
private fun HeroesListItem(
    hero: Hero,
    onHeroClicked: (Hero) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .clickable { onHeroClicked(hero) }
    ) {
        Image(
            painter = rememberAsyncImagePainter(hero.imageUrl),
            contentDescription = "image",
            alignment = Alignment.CenterStart,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(55.dp)
                .padding(
                    start = 5.dp,
                    top = 5.dp,
                    bottom = 5.dp
                )
                .clip(RoundedCornerShape(25.dp))
        )

        Text(
            text = hero.name,
            fontSize = 26.sp,
            modifier = Modifier
                .padding(5.dp)
        )
    }
}

@Composable
private fun ProgressListItem(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(65.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator()
    }
}