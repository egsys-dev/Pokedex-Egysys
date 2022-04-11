package me.dio.pokedexegysysteste.domain

data class Pokemon(
    var number: Int,
    val name: String,
    val types: List<PokemonType>
) {
    val formattedName = name.capitalize()

    val formattedNumber = number.toString().padStart(3, '0')

        val imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/$formattedNumber.png"
        val sprites =  "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png"
}
