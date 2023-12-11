package by.bashlikovvv.heroes_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import by.bashlikovvv.heroes_compose.domain.model.Hero
import by.bashlikovvv.heroes_compose.presentation.details.DetailsScreen
import by.bashlikovvv.heroes_compose.presentation.heroes.HeroesScreen
import by.bashlikovvv.heroes_compose.ui.theme.HeroesComposeTheme
import dagger.hilt.android.AndroidEntryPoint

const val ROUTE_HEROES = "heroes"

const val ROUTE_DETAILS = "details"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigationController = rememberNavController()
            var hero: Hero? = null

            HeroesComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = { ApplicationTopBar() }
                    ) { paddingValues ->
                        NavHost(
                            modifier = Modifier.padding(paddingValues),
                            navController = navigationController,
                            startDestination = ROUTE_HEROES
                        ) {
                            composable(ROUTE_HEROES) {
                                HeroesScreen {
                                    hero = it
                                    navigationController
                                        .navigate(route = ROUTE_DETAILS)
                                }
                            }
                            composable(ROUTE_DETAILS) {
                                DetailsScreen(hero = hero)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ApplicationTopBar(modifier: Modifier = Modifier) {
    NavigationBar(
        modifier = modifier
            .height(50.dp)
    ) {

    }
}