package com.farazrizki13.tutorapp1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.farazrizki13.tutorapp1.R
import com.farazrizki13.tutorapp1.adapter.SettingsMenuAdapter
import com.farazrizki13.tutorapp1.model.SettingsMenuModel
import kotlinx.android.synthetic.main.settings_main.*

class SettingsFragment : Fragment() {

    private lateinit var settingsMenuAdapter: SettingsMenuAdapter

    private val settingsMenuSource = listOf(
        SettingsMenuModel("menu_title", "Account settings"),
        SettingsMenuModel("item_menu", "My profile"),
        SettingsMenuModel("item_menu", "Search History"),
        SettingsMenuModel("menu_title", "Settings"),
        SettingsMenuModel("item_menu", "Chat Settings"),
        SettingsMenuModel("item_menu", "Notification Settings"),
        SettingsMenuModel("item_menu", "Language Settings"),
        SettingsMenuModel("item_menu", "Privacy Settings"),
        SettingsMenuModel("menu_title", "Help"),
        SettingsMenuModel("item_menu", "App information"),
        SettingsMenuModel("item_menu", "Delete Account")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_settings.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            settingsMenuAdapter = SettingsMenuAdapter(settingsMenuSource)
            adapter = settingsMenuAdapter
        }
    }
}