package com.example.codechallenge.util

import android.content.Context
import com.example.codechallenge.model.ItemsRequest
import com.google.gson.Gson

class CacheUtil {
    fun saveCache(context: Context, value: String){
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE).edit()
        sharedPreferences.putString(CACHE, value)
        sharedPreferences.apply()
    }

    fun getCache(context: Context): ItemsRequest?{
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
        val jsonString = sharedPreferences.getString(CACHE, null)
        return if(jsonString == null){
            ItemsRequest(mutableListOf())
        }else{
            Gson().fromJson(jsonString, ItemsRequest::class.java)
        }

    }
}