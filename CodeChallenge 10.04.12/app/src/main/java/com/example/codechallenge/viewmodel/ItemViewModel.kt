package com.example.codechallenge.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codechallenge.model.Item
import com.example.codechallenge.model.ItemsRequest
import com.example.codechallenge.service.RequestItems
import com.example.codechallenge.util.CacheUtil
import com.example.codechallenge.util.InternetUtil

class ItemViewModel : ViewModel() {
    var itemsLiveData = MutableLiveData<ItemsRequest>()
    var itemLiveData = MutableLiveData<Item>()

    fun setItems(items: ItemsRequest?) {
        itemsLiveData.value = items
    }

    fun setItem(item: Item) {
        itemLiveData.value = item
    }

    fun retrieveItems(context: FragmentActivity) {
        if (InternetUtil().isDeviceConnected(context)) {
            RequestItems(context).retrieveItems()
        } else {
            setItems(CacheUtil().getCache(context))
        }
    }
}