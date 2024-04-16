package com.example.pokedex

import com.google.gson.annotations.SerializedName


data class Sprites(

    @SerializedName("front_default")
    val frontDefault : String
)

data class Pokemon(
    val name : String,
    val sprites : Sprites
)
