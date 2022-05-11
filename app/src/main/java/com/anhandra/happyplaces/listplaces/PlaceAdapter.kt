package com.anhandra.happyplaces.listplaces

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anhandra.happyplaces.R
import com.anhandra.happyplaces.database.Place
import com.anhandra.happyplaces.listeners.PlaceClickListener

class PlaceAdapter(val clickListener: PlaceClickListener) :
    RecyclerView.Adapter<ItemPlaceViewHolder>() {

    var data = listOf<Place>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPlaceViewHolder {
        val layoutInflate = LayoutInflater.from(parent.context)
        val view = layoutInflate.inflate(R.layout.item_place_view, parent, false)

        return ItemPlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemPlaceViewHolder, position: Int) {
        val item = data[position]


        holder.placeName.text = item.placeName
        holder.locationName.text = item.location
        holder.overallMood.setImageResource(
            when (item.mood) {
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_0
            }
        )
        holder.cardView.setOnClickListener {
            clickListener.onClick(item)

        }
        holder.cardView.setOnLongClickListener {
            clickListener.onLongClick(item)
        }

    }


    override fun getItemCount(): Int {
        return data.size
    }


}