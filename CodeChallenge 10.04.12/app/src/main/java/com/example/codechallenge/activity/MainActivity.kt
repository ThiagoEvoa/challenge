package com.example.codechallenge.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.codechallenge.R
import com.example.codechallenge.fragment.MainFragment
import com.example.codechallenge.util.NavigationUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationUtil().openView(this, MainFragment())
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            this.finish()
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}