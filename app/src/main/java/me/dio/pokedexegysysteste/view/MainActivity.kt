package me.dio.pokedexegysysteste.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import me.dio.pokedexegysysteste.commom.Constants.NUMBER
import me.dio.pokedexegysysteste.databinding.ActivityMainBinding
import me.dio.pokedexegysysteste.domain.Pokemon
import me.dio.pokedexegysysteste.viewmodel.PokemonViewModel
import me.dio.pokedexegysysteste.viewmodel.PokemonViewModelFactory
import kotlin.random.Random


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding



    private val viewModel by lazy {
        ViewModelProvider(this, PokemonViewModelFactory())
            .get(PokemonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.pokemons.observe(this, Observer {
            loadRecyclerView(it)
        })
    }

    private fun loadRecyclerView(pokemonsList: List<Pokemon?>) {
        val adapterPoke = PokemonAdapter(pokemonsList)
        binding.rvPokemons.layoutManager = GridLayoutManager(this, 2)
        binding.rvPokemons.adapter = adapterPoke


        adapterPoke.setOnItemClickListener(object : PokemonAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {

                val intent = Intent(applicationContext, PokemonDetail::class.java)
                intent.putExtra(NUMBER, pokemonsList[position]!!.imageUrl)
                intent.putExtra("meuOvo", pokemonsList[position]!!.name)
                startActivity(intent)

            }
        })

        binding.btRandom.setOnClickListener(View.OnClickListener {
            val number = pokemonsList.size
            val numberRandom = (Random.nextInt(number)) + 1
            val intent = Intent(applicationContext, PokemonDetail::class.java)
            intent.putExtra(NUMBER, pokemonsList[numberRandom]!!.imageUrl)
            startActivity(intent)

        })
    }
}


