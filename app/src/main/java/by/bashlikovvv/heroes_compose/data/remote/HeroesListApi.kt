package by.bashlikovvv.heroes_compose.data.remote

import by.bashlikovvv.heroes_compose.data.remote.response.HeroesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroesListApi {

    @GET("character")
    suspend fun getHeroesList(
        @Query("page") page: Int = 1
    ): Response<HeroesDto>

}