package me.dio.pokedexegysysteste.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import me.dio.pokedexegysysteste.R
import me.dio.pokedexegysysteste.databinding.PokemonDatailBinding
import me.dio.pokedexegysysteste.domain.Pokemon
import me.dio.pokedexegysysteste.viewmodel.PokemonViewModel
import me.dio.pokedexegysysteste.viewmodel.PokemonViewModelFactory
import kotlin.random.Random


class PokemonDetail : AppCompatActivity() {

    private lateinit var binding: PokemonDatailBinding

    private val viewModel by lazy {
        ViewModelProvider(this, PokemonViewModelFactory())
            .get(PokemonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PokemonDatailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.pokemons.observe(this, Observer {
            loadPokemons(it)
        })
        supportActionBar?.hide()
    }

    @SuppressLint("setText")
    private fun loadPokemons(pokemonsList: List<Pokemon?>) {
        val randomNumber = Random.nextInt(1,150)
        val pokeType1 = pokemonsList[randomNumber]?.types!![0].name.capitalize()
        binding.tvPokemonName.setText(pokemonsList[randomNumber]?.formattedName)
        binding.tvType.setText(pokeType1.capitalize())
        binding.tvType.setTextColor(resources.getColor(R.color.white))

        if(pokemonsList[randomNumber]?.types?.size!! > 1){
            val pokeType2 = pokemonsList[randomNumber]?.types!![1].name.capitalize()
            binding.tvType.setText(pokeType1)
            binding.tvType.setTextColor(resources.getColor(R.color.white))
            binding.tvType2.setText(pokeType2)
            binding.tvType2.setTextColor(resources.getColor(R.color.white))
        }

        Glide.with(this)
            .load(pokemonsList[randomNumber]?.imageUrl)
            .into(binding.ivPokemonImage)
    }
}
