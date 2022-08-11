package com.example.pokedex.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.api.PokemonRepository
import com.example.pokedex.domain.Pokemon

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    var loaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.rvPokemons)

//        val charmander = Pokemon(
//            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png",
//            1,
//            "Charmander",
//
//            listOf(
//                PokemonType("Fire")
//            )
//        )
//
//        val pokemons = listOf(charmander, charmander, charmander, charmander, charmander, charmander)

        if (!loaded) {
            Thread(Runnable {
                loadPokemons()
            }).start() //Thread rodando o metodo de carregar pokemons

            loaded = true //if para carregar somente uma vez pq senao fica carregando toda hora

        }
    }


    private fun loadPokemons() {

        val pokemonsApiResult = PokemonRepository.listPokemons()//requisição

        //caso tenha carregado os pokemons, traz esse resultado
        pokemonsApiResult?.results?.let { it ->

            val pokemons: List<Pokemon?> = it.map { pokemonResult ->
                val number = pokemonResult.url
                    .replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "").toInt()


                val pokemonApiResult = PokemonRepository.getPokemon(number)

                pokemonApiResult?.let {

                    Pokemon(
                        pokemonApiResult.id,
                        pokemonApiResult.name,
                        pokemonApiResult.types.map { type ->
                            type.type
                        }
                    )
                }

            }

            val layoutManager = LinearLayoutManager(this)

            recyclerView.post { //temos que usar as views na mesma thread
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = PokemonAdapter(pokemons)
            }
        }
    }
}





