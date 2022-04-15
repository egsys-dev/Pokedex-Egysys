package me.dio.pokedexegysysteste.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import me.dio.pokedexegysysteste.R
import me.dio.pokedexegysysteste.commom.Constants.IMAGE
import me.dio.pokedexegysysteste.commom.Constants.NAME
import me.dio.pokedexegysysteste.commom.Constants.POSITION
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val pokeSearch: MenuItem = menu.findItem(R.id.search_pokemon)
        val searchView = pokeSearch.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filterPokemon(filter = newText ?: "")
                return true
            }
        })
        return true
    }

    private fun loadRecyclerView(pokemonsList: List<Pokemon?>) {
        val adapterPoke = PokemonAdapter(pokemonsList)
        binding.rvPokemons.layoutManager = GridLayoutManager(this, 2)
        binding.rvPokemons.adapter = adapterPoke

        adapterPoke.setOnItemClickListener(object : PokemonAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {

                val intent = Intent(applicationContext, PokemonDetail::class.java)
                intent.putExtra(IMAGE, pokemonsList[position]!!.imageUrl)
                intent.putExtra(NAME, pokemonsList[position]!!.name)
                intent.putExtra(POSITION, position.toString())

                startActivity(intent)
            }
        })
        binding.btRandom.setOnClickListener(View.OnClickListener {
            val number = pokemonsList.size
            val numberRandom = (Random.nextInt(number)) + 1
            val intent = Intent(applicationContext, PokemonDetail::class.java)
            intent.putExtra(IMAGE, pokemonsList[numberRandom]!!.imageUrl)
            intent.putExtra(NAME, pokemonsList[numberRandom]!!.name)
            intent.putExtra(POSITION, numberRandom.toString())
            startActivity(intent)

        })
    }
}


