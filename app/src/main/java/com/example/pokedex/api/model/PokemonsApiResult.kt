package com.example.pokedex.api.model

import com.example.pokedex.domain.PokemonType

data class PokemonsApiResult ( //contagem, proximo pokemon, pokemon anterior que serão recebidos
    val count: Int,
    val next: String?, //são opcionais por isso aceitam nulo '?'
    val previous: String?, //são opcionais por isso aceitam nulo '?'
    val results: List<PokemonResult>

)

data class PokemonResult( //nome do pokemon e url do pokemon que serão recebidas da api
    val name: String,
    val url: String
)

data class PokemonApiResult( //id, nome e tipo do pokemon
    val id: Int,
    val name: String,
    val types: List <PokemonTypeSlot>
)

data class PokemonTypeSlot( //slots de tipo de pokemon
    val slot: Int,
    val type: PokemonType


)