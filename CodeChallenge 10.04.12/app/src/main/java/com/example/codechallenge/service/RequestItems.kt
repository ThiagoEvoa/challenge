package com.example.codechallenge.service

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.example.codechallenge.model.ItemsRequest
import com.example.codechallenge.util.CacheUtil
import com.example.codechallenge.util.LoadingUtil
import com.example.codechallenge.viewmodel.ItemViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestItems(context: FragmentActivity) : Callback<ItemsRequest> {
    private val viewModel: ItemViewModel =
        ViewModelProviders.of(context).get(ItemViewModel::class.java)
    private val myContext = context
    private val loadingUtil = LoadingUtil()

    fun retrieveItems() {
        loadingUtil.showLoading(myContext)
        val call = RetrofitConfig().requestItems().retrieveItems()
        call.enqueue(this)
    }
    override fun onFailure(call: Call<ItemsRequest>, t: Throwable) {
        Log.e("Error", t.toString())
    }

    override fun onResponse(call: Call<ItemsRequest>, response: Response<ItemsRequest>) {
        loadingUtil.dismissLoading()
        when (response.code()) {
            200 -> {
                CacheUtil().saveCache(myContext.baseContext, Gson().toJson(response.body()!!))
                viewModel.setItems(response.body())
            }
            else -> {
                viewModel.setItems(ItemsRequest(mutableListOf()))
            }
        }
    }

}