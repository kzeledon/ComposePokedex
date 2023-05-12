package com.kzeledon.jetpackcomposepokedex.di

import com.kzeledon.jetpackcomposepokedex.data.remote.PokeAPI
import com.kzeledon.jetpackcomposepokedex.repository.PokemonRepository
import com.kzeledon.jetpackcomposepokedex.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokemonRepository(api: PokeAPI) = PokemonRepository(api)

    @Singleton
    @Provides
    fun providePokeAPI() : PokeAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PokeAPI::class.java)
    }

}