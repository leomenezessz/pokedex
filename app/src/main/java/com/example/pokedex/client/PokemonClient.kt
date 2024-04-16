package com.example.pokedex.client

import com.example.pokedex.client.HttpClient.getRetrofitInstance
import com.example.pokedex.services.PokemonService

object PokemonClient {
    private const val POKEMON_URL = "https://pokeapi.co/api/v2/"
    val pokemonService: PokemonService = getRetrofitInstance(POKEMON_URL).create(
                    PokemonService::class.java)
}
