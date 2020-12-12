package com.farazrizki13.tutorapp1.adapter

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.farazrizki13.tutorapp1.R
import com.farazrizki13.tutorapp1.model.UpcomingModel
import kotlinx.android.synthetic.main.upcoming_card.view.*


class UpcomingAdapter (val item : List<UpcomingModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class UpcomingViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {
        fun bind(upcomingModel: UpcomingModel) {
            with(itemView) {
                val img = upcomingModel.img
                val title = upcomingModel.title
                val imgCard : ImageView = itemView.findViewById(R.id.img_card_upcoming)
                val titleCard : TextView = itemView.findViewById(R.id.txt_title_card_upcoming)

                titleCard.text = title

                val requestOption = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

                Glide.with(itemView.context)
                    .applyDefaultRequestOptions(requestOption)
                    .load(img)
                    .into(imgCard)

                val handler = Handler(Looper.getMainLooper())

                itemView.setOnClickListener {
                    itemView.view_background.animate().alpha(1F).setDuration(300).start()
                    itemView.container_txt_upcoming.animate().alpha(1F).setDuration(300).start()

                    handler.postDelayed({
                        itemView.view_background.animate().alpha(0F).setDuration(300).start()
                        itemView.container_txt_upcoming.animate().alpha(0F).setDuration(300).start()
                    },2000)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UpcomingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.upcoming_card, parent, false)
        )
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is UpcomingViewHolder -> holder.bind(item[position])
        }
    }

}