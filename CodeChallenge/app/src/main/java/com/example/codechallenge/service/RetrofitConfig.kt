package com.example.codechallenge.service

import com.example.codechallenge.util.HOST
import com.example.codechallenge.util.URL_ITEMS
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(HOST)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun requestItems(): IRequestItems {
        return this.retrofit.create(IRequestItems::class.java)
    }
}