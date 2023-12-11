package by.bashlikovvv.heroes_compose.domain.repository

import by.bashlikovvv.heroes_compose.domain.model.Hero

interface IHeroesRepository {

    suspend fun getHeroes(page: Int): List<Hero>

}