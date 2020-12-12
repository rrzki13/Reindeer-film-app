package com.farazrizki13.tutorapp1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.farazrizki13.tutorapp1.R
import com.farazrizki13.tutorapp1.`interface`.NavigationHost
import com.farazrizki13.tutorapp1.model.SearchListModel
import kotlinx.android.synthetic.main.card_search_list.view.*

class SearchResultAdapter(val item : List<SearchListModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class SearchResultViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {
        fun bind(searchListModel: SearchListModel) {
            with(itemView) {
                val title = searchListModel.Title
                val code = searchListModel.imdbID
                val gambar = searchListModel.Poster

                val tvTitle = itemView.tv_card_search_list
                val imgTitle = itemView.iv_card_search_list

                val requestOption = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

                Glide.with(itemView.context)
                    .applyDefaultRequestOptions(requestOption)
                    .load(gambar)
                    .into(imgTitle)

                tvTitle.text = title

                itemView.setOnClickListener {
                    (context as NavigationHost).testWork(code)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchResultViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_search_list, parent, false)
        )
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SearchResultViewHolder -> holder.bind(item[position])
        }
    }

}