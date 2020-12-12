package com.farazrizki13.tutorapp1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.farazrizki13.tutorapp1.`interface`.NavigationHost
import com.farazrizki13.tutorapp1.fragment.*
import com.luseen.spacenavigation.SpaceItem
import com.luseen.spacenavigation.SpaceOnClickListener
import kotlinx.android.synthetic.main.main_bottom_bar.*

class MainActivity : AppCompatActivity(), NavigationHost {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spaceNavigation = space
        spaceNavigation.initWithSaveInstanceState(savedInstanceState)
        spaceNavigation.addSpaceItem(SpaceItem("Home", R.drawable.ic_home))
        spaceNavigation.addSpaceItem(SpaceItem("Cog", R.drawable.ic_cog))
        spaceNavigation.showIconOnly()

        spaceNavigation.setActiveSpaceItemColor(ContextCompat.getColor(this, R.color.colorAccent))
        spaceNavigation.setCentreButtonIconColorFilterEnabled(false)

        spaceNavigation.setCentreButtonColor(ContextCompat.getColor(this, R.color.white))
        spaceNavigation.setCentreButtonRippleColor(ContextCompat.getColor(this@MainActivity, R.color.colorAccent))

        spaceNavigation.setSpaceOnClickListener(object : SpaceOnClickListener{
            override fun onCentreButtonClick() {
                val fragment = SearchFragment()
                (this@MainActivity).navigateTo(fragment, false, fade = false)
            }

            override fun onItemReselected(itemIndex: Int, itemName: String?) {
                when (itemIndex) {
                    0 -> {
                        (this@MainActivity).navigateTo(HomeFragment(), false, fade = false)
                    }
                    1 -> {
                        (this@MainActivity).navigateTo(SettingsFragment(), false, fade = false)
                    }
                }
            }

            override fun onItemClick(itemIndex: Int, itemName: String?) {
                when (itemIndex) {
                    0 -> {
                        (this@MainActivity).navigateTo(HomeFragment(), false , fade = false)
                    }
                    1 -> {
                        (this@MainActivity).navigateTo(SettingsFragment(), false, fade = false)
                    }
                }
            }
        })

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.container_all, SplashScreenFragment())
                .commit()
        }

    }

    override fun navigateTo(fragment: Fragment, addToBackstack: Boolean, fade: Boolean, search : Boolean, backSearch : Boolean) {
        var customAnimationIn = R.anim.translate_right
        var customAnimationOut = R.anim.translate_left

        when {
            fade -> {
                customAnimationIn = R.anim.fade_in
                customAnimationOut = R.anim.fade_out
            }
            search -> {
                customAnimationIn = R.anim.translate_down
                customAnimationOut = R.anim.translate_up
            }
            backSearch -> {
                customAnimationIn = R.anim.translate_up
                customAnimationOut = R.anim.translate_down
            }
        }

        val transaction = supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(customAnimationIn, customAnimationOut)
            .replace(R.id.container_all, fragment)

        if (addToBackstack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

    override fun testWork(code: String) {
        val fragment = FilmDeskripsionFragment()
        val args = Bundle()
        args.putString("code", code)
        fragment.arguments = args

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            .replace(R.id.container_all, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun moveActivity(intent: Intent) {
        startActivity(intent)
    }


}