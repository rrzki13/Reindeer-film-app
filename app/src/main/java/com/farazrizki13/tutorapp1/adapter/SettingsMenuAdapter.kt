package com.farazrizki13.tutorapp1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.farazrizki13.tutorapp1.R
import com.farazrizki13.tutorapp1.model.SettingsMenuModel
import kotlinx.android.synthetic.main.settings_menu.view.*

class SettingsMenuAdapter(private val items : List<SettingsMenuModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class SettingsMenuViewHolder(private val itemview : View) : RecyclerView.ViewHolder(itemview) {
        fun bind(settingsMenuModel: SettingsMenuModel) {
            with(itemview) {
                val type = settingsMenuModel.type
                val title = settingsMenuModel.title
                val titleCard = itemview.menu_settings_title
                val iconCard = itemview.ic_menu_settings
                val container = itemview.container_setting_menu
                titleCard.text = title
                when (type) {
                    "menu_title" -> {
                        container.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
                        iconCard.visibility = View.GONE
                    }
                    "item_menu" -> {
                        itemview.setOnClickListener {
                            Toast.makeText(context, "Menu item clicked", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SettingsMenuViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.settings_menu, parent, false)
        )
    }

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is SettingsMenuViewHolder -> holder.bind(items[position])
        }
    }

}