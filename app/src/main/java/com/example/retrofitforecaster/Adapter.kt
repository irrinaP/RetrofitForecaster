package com.example.retrofitforecaster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ContactItemDiffCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem) = oldItem == newItem
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem) = oldItem == newItem
}

class ViewHolderCold(view: View) : RecyclerView.ViewHolder(view) {
    private val dateTime: TextView = view.findViewById(R.id.datetime_text)
    private val temperature: TextView = view.findViewById(R.id.temperature_text)
    val iconImageView: ImageView = view.findViewById(R.id.cold_icon_image_view)

    fun bindTo(weather: ListItem) {
        temperature.text = weather.main.temp.toString()
        dateTime.text = weather.dt_txt

        Picasso.get().load(R.drawable.snow).into(iconImageView)
    }
}

class ViewHolderHot(view: View) : RecyclerView.ViewHolder(view) {
    private val dateTime: TextView = view.findViewById(R.id.datetime_text)
    private val temperature: TextView = view.findViewById(R.id.temperature_text)
    val iconImageView: ImageView = view.findViewById(R.id.hot_icon_image_view)

    fun bindTo(weather: ListItem) {
        temperature.text = weather.main.temp.toString()
        dateTime.text = weather.dt_txt

        Picasso.get().load(R.drawable.sun).into(iconImageView)
    }
}

class Adapter : ListAdapter<ListItem, RecyclerView.ViewHolder>(ContactItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.rview_item_hot, parent, false)
            view.setBackgroundResource(R.drawable.hot_background)
            ViewHolderHot(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.rview_item_cold, parent, false)
            view.setBackgroundResource(R.drawable.cold_background)
            ViewHolderCold(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = currentList[position]
        if (data.main.temp > 0) {
            val viewHolderHot: ViewHolderHot = holder as ViewHolderHot
            viewHolderHot.bindTo(data)
        } else {
            val viewHolderCold: ViewHolderCold = holder as ViewHolderCold
            viewHolderCold.bindTo(data)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].main.temp > 0) {
            0
        } else {
            1
        }
    }
}
