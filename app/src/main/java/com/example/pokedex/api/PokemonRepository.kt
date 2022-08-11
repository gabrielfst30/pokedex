package com.example.pokedex.api
import android.util.Log
import com.example.pokedex.api.model.PokemonApiResult
import com.example.pokedex.api.model.PokemonsApiResult
import com.example.pokedex.domain.Pokemon
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

    fun listPokemons(limit: Int = 151): PokemonsApiResult? { //chamando a lista de pokemons da interface e definindo um limite

       val call = service.listPokemons(limit)

        return call.execute().body()

    }

    fun getPokemon(number :Int): PokemonApiResult? { //chamando a lista de pokemons da interface e definindo um limite

       val call = service.getPokemon(number)

        return call.execute().body()

    }
}


//https://pokeapi.co/api/v2/pokemon/?limit=300

