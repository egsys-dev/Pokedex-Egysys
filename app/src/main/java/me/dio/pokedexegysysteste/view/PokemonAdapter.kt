package me.dio.pokedexegysysteste.view



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.dio.pokedexegysysteste.R
import me.dio.pokedexegysysteste.domain.Pokemon

import me.dio.pokedexegysysteste.domain.PokemonListItem

class PokemonAdapter(

    private val pokeitems: List<Pokemon?>
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private lateinit var pListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        pListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return ViewHolder(view, pListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val number =
//            if(pokeitems[position].url.endsWith("/")) {
//                pokeitems[position].url.dropLast(1).takeLastWhile { it.isDigit() }
//            } else {
//                pokeitems[position].url.takeLastWhile { it.isDigit() }
//            }
        val item = PokemonListItem(
        number = pokeitems[position]!!.number,
            name = pokeitems[position]!!.name
        )

        holder.bindView(item)
    }
    override fun getItemCount(): Int {
        return pokeitems.size
    }


    class ViewHolder(itemView: View, pListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: PokemonListItem) = with(itemView) {
            val ivPokemon = findViewById<ImageView>(R.id.ivPokemon)
            val tvNumber = findViewById<TextView>(R.id.tvNumber)
            val tvName = findViewById<TextView>(R.id.tvName)


            item?.let {
                Glide.with(itemView.context)
                    .load(it.imageUrl)
                    .into(ivPokemon)

                tvNumber.text = "Nº ${item.formattedNumber}"
                tvName.text = item.formattedName

                }
            }
        }
    }

