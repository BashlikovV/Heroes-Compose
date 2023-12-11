package by.bashlikovvv.heroes_compose.data.repository

import by.bashlikovvv.heroes_compose.data.remote.HeroesListApi
import by.bashlikovvv.heroes_compose.domain.model.Hero
import by.bashlikovvv.heroes_compose.domain.repository.IHeroesRepository

class HeroesRepository(
    private val heroesListApi: HeroesListApi
) : IHeroesRepository {

    override suspend fun getHeroes(page: Int): List<Hero> {
        val heroes = heroesListApi.getHeroesList(page).body()?.data?.map {
            Hero(
                id = it?.Id ?: 0,
                films = it?.films?.map { film ->
                    film.toString()
                } ?: emptyList(),
                games = it?.videoGames?.map { game ->
                    game.toString()
                } ?: emptyList(),
                sourceUrl = it?.sourceUrl ?: "",
                name = it?.name ?: "",
                imageUrl = it?.imageUrl ?: ""
            )
        }
        return heroes ?: emptyList()
    }

}