package com.example.pokedex

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pokedex.client.PokemonClient
import com.example.pokedex.services.PokemonService
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val pokemonService: PokemonService = PokemonClient.pokemonService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val findPokemonButton = findViewById<Button>(R.id.button)
        val imgPokemon = findViewById<ImageView>(R.id.imageView)
        val editTextPokemonName = findViewById<EditText>(R.id.edit_text_pokemon_name)


        findPokemonButton.setOnClickListener { v: View ->

            val name = editTextPokemonName.getText().toString()

            if (name.isEmpty()){
                Toast.makeText(v.context, "Please type a pokemon name...", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            pokemonService.findPokemon(name)?.enqueue(object : Callback<Pokemon?> {
                override fun onResponse(call: Call<Pokemon?>, response: Response<Pokemon?>) {
                    if (response.isSuccessful && response.body() != null) {
                        Toast.makeText(v.context, response.body()!!.name, Toast.LENGTH_LONG).show()
                        Picasso.get().load(response.body()!!.sprites.frontDefault).into(imgPokemon)
                    } else {
                        Toast.makeText(v.context, "Request error...", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Pokemon?>, t: Throwable) {
                    Toast.makeText(v.context, "Request failure...", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}