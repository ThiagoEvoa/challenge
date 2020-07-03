package com.example.codechallenge.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.codechallenge.R
import com.example.codechallenge.activity.MainActivity
import com.example.codechallenge.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_detail.view.*


class DetailFragment : Fragment() {
    private lateinit var fragmentView: View
    private val viewModel: ItemViewModel by lazy {
        ViewModelProviders.of(activity!!).get(ItemViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.fragment_detail, container, false)
        viewModel.itemLiveData.observe(activity!!, Observer {
            when (it.reward?.trophy) {
                "gold_medal" -> setMedalColor(fragmentView.img_medal, inflater.context, R.color.colorGold)
                "silver_medal" -> setMedalColor(fragmentView.img_medal, inflater.context, R.color.colorSilver)
                "bronze_medal" -> setMedalColor(fragmentView.img_medal, inflater.context, R.color.colorBronze)
            }
            fragmentView.txt_title.text = it.title
            fragmentView.txt_description.text = it.description
            fragmentView.txt_type.text = it.type
            fragmentView.txt_goal.text = it.goal.toString()
            fragmentView.txt_points.text = it.reward?.points.toString()
        })

        return fragmentView
    }

    override fun onResume() {
        super.onResume()
        showBackButton()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            ((activity) as MainActivity).onBackPressed()
        } else {
            super.onOptionsItemSelected(item)
        }

        return true
    }

    private fun showBackButton(){
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as MainActivity).supportActionBar?.show()
    }

    private fun setMedalColor(medal: AppCompatImageView, context: Context, color: Int) {
        medal.setColorFilter(
            ContextCompat.getColor(
                context,
                color
            )
        )
    }
}