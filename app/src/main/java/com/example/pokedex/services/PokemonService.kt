package com.example.pokedex.services

import com.example.pokedex.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("pokemon/{name}")
    fun findPokemon(@Path("name") name: String?): Call<Pokemon?>?
}
