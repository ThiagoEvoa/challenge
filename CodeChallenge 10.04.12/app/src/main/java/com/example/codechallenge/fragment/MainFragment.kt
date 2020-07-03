package com.example.codechallenge.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codechallenge.R
import com.example.codechallenge.activity.MainActivity
import com.example.codechallenge.adapter.ItemAdapter
import com.example.codechallenge.model.Item
import com.example.codechallenge.util.CacheUtil
import com.example.codechallenge.util.NavigationUtil
import com.example.codechallenge.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment() {
    private lateinit var fragmentView: View
    private val viewModel: ItemViewModel by lazy {
        ViewModelProviders.of(activity!!).get(ItemViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.itemsLiveData.observe(activity!!, Observer {
            it.items?.let { list -> setAdapter(list) }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_main, container, false)
        fragmentView.swipe.setOnRefreshListener {
            fragmentView.swipe.isRefreshing = false
            refreshList()
        }
        return fragmentView
    }

    override fun onResume() {
        super.onResume()
        hideBackButton()
        refreshList()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            ((activity) as MainActivity).onBackPressed()
        } else {
            super.onOptionsItemSelected(item);
        }

        return true
    }

    private fun hideBackButton() {
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    fun onItemClicked(position: Int) {
        viewModel.itemsLiveData.value?.items?.get(position)?.let { viewModel.setItem(it) }
        NavigationUtil().openView(activity!!, DetailFragment())
    }

    private fun refreshList() {
        viewModel.retrieveItems(activity!!)
    }

    private fun setAdapter(list: MutableList<Item>) {
        recycler_view.layoutManager = LinearLayoutManager(this.activity?.baseContext)
        recycler_view.adapter = ItemAdapter(activity!!, list)
    }
}