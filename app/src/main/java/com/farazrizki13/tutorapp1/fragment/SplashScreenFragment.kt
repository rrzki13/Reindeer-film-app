package com.farazrizki13.tutorapp1.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.farazrizki13.tutorapp1.R
import com.farazrizki13.tutorapp1.`interface`.NavigationHost
import com.luseen.spacenavigation.SpaceNavigationView

class SplashScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.splash_screen_fragment,container, false)
        val space : SpaceNavigationView = activity!!.findViewById(R.id.space)
        space.visibility = View.GONE

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            (activity as NavigationHost).navigateTo(HomeFragment(), false, fade = true)
        },3000)

        return view
    }
}