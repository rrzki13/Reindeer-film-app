package com.farazrizki13.tutorapp1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.farazrizki13.tutorapp1.R
import com.farazrizki13.tutorapp1.model.SearchGenreModel
import kotlinx.android.synthetic.main.search_genre_card.view.*

class SearchGenreAdapter(val items : List<SearchGenreModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class SearchGenreViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {
        fun bind(searchGenreModel: SearchGenreModel) {
            with(itemView) {
                val color = searchGenreModel.color
                val title = searchGenreModel.title
                val card = itemView.card_genre
                val titleCard = itemView.txt_title_card_genre

                card.setCardBackgroundColor(ContextCompat.getColor(context, color))
                titleCard.text = title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchGenreViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.search_genre_card, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SearchGenreViewHolder -> holder.bind(items[position])
        }
    }

}