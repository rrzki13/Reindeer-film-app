package com.farazrizki13.tutorapp1.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.farazrizki13.tutorapp1.R
import com.farazrizki13.tutorapp1.`interface`.NavigationHost
import com.farazrizki13.tutorapp1.model.YouLikeModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.you_like_card.view.*

class YouLikeAdapter(val item : List<YouLikeModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class YouLikeViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {
        fun bind (youLikeModel: YouLikeModel) {
            with(itemView) {
                val image = youLikeModel.img
                val code = youLikeModel.code

                val cardImg : ImageView = itemView.findViewById(R.id.img_card_you_like)

                val requestOption = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

                Glide.with(itemView.context)
                    .applyDefaultRequestOptions(requestOption)
                    .load(image)
                    .into(cardImg)

                itemView.setOnClickListener {
                    (context as NavigationHost).testWork(code)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return YouLikeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.you_like_card, parent, false)
        )
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is YouLikeViewHolder -> holder.bind(item[position])
        }
    }

}