package me.dio.pokedexegysysteste.domain

import java.util.*

data class Pokemon(
    var number: Int,
    val name: String,
    val types: List<PokemonType>
) {
    val formattedName = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

    val formattedNumber = number.toString().padStart(3, '0')

        val imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/$formattedNumber.png"
//        val sprites =  "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png"
}
