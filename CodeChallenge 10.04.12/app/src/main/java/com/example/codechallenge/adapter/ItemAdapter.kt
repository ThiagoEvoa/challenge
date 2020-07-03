package com.example.codechallenge.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.codechallenge.R
import com.example.codechallenge.fragment.DetailFragment
import com.example.codechallenge.fragment.MainFragment
import com.example.codechallenge.model.Item
import kotlinx.android.synthetic.main.item_view.view.*

class ItemAdapter(private val context: FragmentActivity, private val objects: MutableList<Item>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return objects.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = objects[position].title
        holder.description.text = objects[position].description
        when (objects[position].reward?.trophy) {
            "gold_medal" -> setMedalColor(holder.imgMedal, R.color.colorGold)
            "silver_medal" -> setMedalColor(holder.imgMedal, R.color.colorSilver)
            "bronze_medal" -> setMedalColor(holder.imgMedal, R.color.colorBronze)
        }

        holder.viewItem.setOnClickListener {
            ((context.supportFragmentManager.fragments[0] as MainFragment)).onItemClicked(position)
        }
    }

    private fun setMedalColor(medal: AppCompatImageView, color: Int){
        medal.setColorFilter(
            ContextCompat.getColor(
                context,
                color
            )
        )
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.txt_title
        var description: TextView = view.txt_description
        var imgMedal: AppCompatImageView = view.img_medal
        var viewItem: LinearLayout = view.view_item
    }
}