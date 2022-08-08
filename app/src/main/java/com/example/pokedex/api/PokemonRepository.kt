package com.example.pokedex.api
import android.util.Log
import com.example.pokedex.api.model.PokemonApiResult
import com.example.pokedex.api.model.PokemonsApiResult
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

object PokemonRepository { //transformando em object para impedir que vários repositors sejam construídos

    private val service: PokemonService

    init { //função de inicialização

        val retrofit = Retrofit.Builder() //fazendo a requisição com o retrofit
            .baseUrl("https://pokeapi.co/api/v2/") //pegando a base url
            .addConverterFactory(GsonConverterFactory.create()) //convertendo o json para gson
            .build() //buildando

        service = retrofit.create(PokemonService::class.java) //implementará todos os metodos da interface pokemon service

    }

    fun listPokemons(limit: Int = 300) { //chamando a lista de pokemons da interface e definindo um limite

       val call = service.listPokemons(limit)

        call.enqueue(object : Callback<PokemonsApiResult>{ //callback trazendo a apiresult

            override fun onResponse(
                call: Call<PokemonsApiResult>,
                response: Response<PokemonsApiResult>
            ) {
               Log.d("POKEMON_API", "Pokémons list loaded.") //deu bom
            }

            override fun onFailure(call: Call<PokemonsApiResult>, t: Throwable) {
                Log.e("POKEMON_API", "Error loading pokemons list.", t) //deu ruim
            }

        } )

    }
}


//https://pokeapi.co/api/v2/pokemon/?limit=300

