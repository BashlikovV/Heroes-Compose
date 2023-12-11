package by.bashlikovvv.heroes_compose.data.di

import by.bashlikovvv.heroes_compose.data.remote.HeroesListApi
import by.bashlikovvv.heroes_compose.data.repository.HeroesRepository
import by.bashlikovvv.heroes_compose.domain.repository.IHeroesRepository
import by.bashlikovvv.heroes_compose.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, factory: Converter.Factory): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(factory)
        .build()

    @Provides
    @Singleton
    fun provideHeroesListApi(retrofit: Retrofit): HeroesListApi {
        return retrofit.create(HeroesListApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHeroesRepository(heroesListApi: HeroesListApi): IHeroesRepository {
        return HeroesRepository(heroesListApi)
    }
}