package com.kzeledon.jetpackcomposepokedex.repository

import com.kzeledon.jetpackcomposepokedex.data.remote.PokeAPI
import com.kzeledon.jetpackcomposepokedex.data.remote.responses.Pokemon
import com.kzeledon.jetpackcomposepokedex.data.remote.responses.PokemonList
import com.kzeledon.jetpackcomposepokedex.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeAPI
) {
    suspend fun getPokemonList(limit: Int, offset: Int) : Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error("An error occurred.")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonInfo(pokemonName: String) : Resource<Pokemon> {
        val response = try {
            api.getPokemon(pokemonName)
        } catch (e: Exception) {
            return Resource.Error("An error occurred.")
        }
        return Resource.Success(response)
    }
}