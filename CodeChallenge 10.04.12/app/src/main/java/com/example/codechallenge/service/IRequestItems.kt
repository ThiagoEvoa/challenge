package com.example.codechallenge.service

import com.example.codechallenge.model.ItemsRequest
import com.example.codechallenge.util.URL_ITEMS
import retrofit2.Call;
import retrofit2.http.GET

interface IRequestItems {
    @GET(URL_ITEMS)
    fun retrieveItems(): Call<ItemsRequest>
}