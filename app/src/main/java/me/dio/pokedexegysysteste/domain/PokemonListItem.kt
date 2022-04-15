package me.dio.pokedexegysysteste.domain

import java.util.Locale

data class PokemonListItem(
    var number: Int,
    var name: String,
) {
    val formattedName =
        name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

    val formattedNumber = number.toString().padStart(3, '0')
    val imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/$formattedNumber.png"

}

