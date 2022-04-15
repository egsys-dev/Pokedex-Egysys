package me.dio.pokedexegysysteste.domain

data class Pokemon(
    var number: Int,
    val name: String = "",
    val types: List<PokemonType>
) {

    val formattedNumber = number.toString().padStart(3, '0')

    val imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/$formattedNumber.png"

}

