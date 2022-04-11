package me.dio.pokedexegysysteste.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import me.dio.pokedexegysysteste.R
import me.dio.pokedexegysysteste.commom.Constants.NUMBER
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
        loadPokemonPosition()
        loadPokemons()
        supportActionBar?.hide()
        loadPokemonPosition()
    }

    @SuppressLint("setText")
    private fun loadPokemons() {
        Glide.with(this).load(intent.getStringExtra(NUMBER)).into(binding.ivPokemonImage)
    }
    private fun loadPokemonPosition(){
        Glide.with(this).load(intent.getStringExtra(NUMBER)).into(binding.ivPokemonImage)
        binding.tvPokemonName.setText(intent.getStringExtra("meuOvo"))

    }

}


