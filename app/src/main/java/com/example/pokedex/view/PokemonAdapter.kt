package com.example.pokedex.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.domain.Pokemon
import java.util.*

class PokemonAdapter(
    private val items: List<Pokemon?>
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false) //inflando o layout

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) { //bind view sabe como compor os itens sa view
        val item =
            items[position] //pegando o item da lista pela posição para que o viewholder mostre

        holder.bindView(item)
    }

    override fun getItemCount(): Int {
        return items.size //qnt de itens
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Pokemon?) = with(itemView) {
            val ivPokemon = findViewById<ImageView>(R.id.ivPokemon)
            val tvNumber = findViewById<TextView>(R.id.tvNumber)
            val tvName = findViewById<TextView>(R.id.tvName)
            val tvType1 = findViewById<TextView>(R.id.tvType1)
            val tvType2 = findViewById<TextView>(R.id.tvType2)

            //TODO: Load image with Glide (biblioteca de carregamento de imagens)

            item?.let {

                Glide.with(itemView.context).load(it.imageUrl).into(ivPokemon) //carregamento rapido de imagens, sem ele fica muito lento!

                tvNumber.text = "Nº ${item.formattedNumber}"
                tvName.text = item.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                tvType1.text = item.types[0].name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

                if (item.types.size > 1) { //verificação para caso não tenha dois tipos sumir a segunda view
                    tvType2.visibility = View.VISIBLE
                    tvType2.text = item.types[1].name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                } else {
                    tvType2.visibility = View.GONE
                }


            }


        }

    }


}