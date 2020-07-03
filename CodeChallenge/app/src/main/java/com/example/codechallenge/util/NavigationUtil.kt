package com.example.codechallenge.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.codechallenge.R

class NavigationUtil {
    fun openView(context: FragmentActivity, fragment: Fragment){
        val transaction =  context.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.replace_fragment, fragment).addToBackStack(null).commit()
    }
}