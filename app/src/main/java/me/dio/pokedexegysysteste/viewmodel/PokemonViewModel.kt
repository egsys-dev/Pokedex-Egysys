package me.dio.pokedexegysysteste.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.dio.pokedexegysysteste.api.model.PokemonRepository
import me.dio.pokedexegysysteste.domain.Pokemon



class PokemonViewModel : ViewModel() {
    var pokemons = MutableLiveData<List<Pokemon?>>()

    init {
        Thread(Runnable {
            loadPokemons()
        }).start()
    }

    private fun loadPokemons() {
        val pokemonsApiResult = PokemonRepository.listPokemons()

        pokemonsApiResult?.results?.let {
            pokemons.postValue(it.map { pokemonResult ->
                val number = pokemonResult.url
                    .replace("https://pokeapi.co/ap" +
                            "i/v2/pokemon/", "")
                    .replace("/", "").toInt()

                val pokemonsApiResult = PokemonRepository.getPokemon(number)

                pokemonsApiResult?.let {
                    Pokemon(
                        number =    pokemonsApiResult.id,
                        name =      pokemonsApiResult.name,
                        types =     pokemonsApiResult.types.map { type ->
                            type.type
                        },
                    )
                }
            })
        }
    }
}