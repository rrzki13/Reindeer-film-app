package com.farazrizki13.tutorapp1.`interface`

import android.content.Intent
import androidx.fragment.app.Fragment

interface NavigationHost {
    fun navigateTo(fragment: Fragment, addToBackstack: Boolean, fade : Boolean, search : Boolean = false, backSearch : Boolean = false)

    fun testWork(code : String)

    fun moveActivity(intent : Intent)
}