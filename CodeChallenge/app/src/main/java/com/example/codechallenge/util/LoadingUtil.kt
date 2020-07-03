package com.example.codechallenge.util

import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.example.codechallenge.R

class LoadingUtil {
    private lateinit var alertDialog: AlertDialog

    fun showLoading(context: FragmentActivity){
        val progressBar = ProgressBar(context)

        val alertBuilder = AlertDialog.Builder(context)
        alertBuilder.setCancelable(false)
        alertBuilder.setView(progressBar)

        alertDialog = alertBuilder.create()
        alertDialog.window?.setBackgroundDrawableResource(R.drawable.transparent_rounded_corner)
        alertDialog.show()
    }

    fun dismissLoading(){
        if(::alertDialog.isInitialized)
            alertDialog.dismiss()
    }
}