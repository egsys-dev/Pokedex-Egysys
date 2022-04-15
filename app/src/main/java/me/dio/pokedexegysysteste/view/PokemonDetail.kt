package me.dio.pokedexegysysteste.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import me.dio.pokedexegysysteste.commom.Constants.IMAGE
import me.dio.pokedexegysysteste.commom.Constants.NAME
import me.dio.pokedexegysysteste.commom.Constants.POSITION
import me.dio.pokedexegysysteste.databinding.PokemonDatailBinding
import me.dio.pokedexegysysteste.domain.Pokemon
import me.dio.pokedexegysysteste.viewmodel.PokemonViewModel
import me.dio.pokedexegysysteste.viewmodel.PokemonViewModelFactory

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
        viewModel.pokemons.observe(this, Observer {
            loadPokemonPosition(it)
        })
    }
    @SuppressLint("setText")
    private fun loadPokemons(pokemons : List<Pokemon?>) {

        val image = intent.getStringExtra(IMAGE)
        val name = intent.getStringExtra(NAME)
        val position = intent.getStringExtra(POSITION)!!.toInt()

        val type1 = pokemons[position]!!.types[0].name
        val typeSize = pokemons[position]!!.types.size

        with(binding) {
            tvPokemonName.setText(name)
            tvType.setText(type1)
            if(typeSize > 1){
                val type2 = pokemons[position]!!.types[1].name
                tvType2.setText(type2)
            }
        }
        Glide.with(this)
            .load(image)
            .into(binding.ivPokemonImage)
    }
    private fun loadPokemonPosition(pokemons : List<Pokemon?>) {
        val image = intent.getStringExtra(IMAGE)
        val name = intent.getStringExtra(NAME)
        val position = intent.getStringExtra(POSITION)!!.toInt()

        val type1 = pokemons[position]!!.types[0].name
        val typeSize = pokemons[position]!!.types.size

        with(binding) {
            tvPokemonName.setText(name)
            tvType.setText(type1)
            if(typeSize > 1){
                val type2 = pokemons[position]!!.types[1].name
                tvType2.setText(type2)
            }
        }
        Glide.with(this)
            .load(image)
            .into(binding.ivPokemonImage)
    }
}


