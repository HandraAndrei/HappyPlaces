package com.anhandra.happyplaces

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ItemPlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val placeName: TextView = itemView.findViewById(R.id.place_name_tv)
    val locationName: TextView = itemView.findViewById(R.id.place_location)
    val overallMood: ImageView = itemView.findViewById(R.id.mood_image)
    val cardView: CardView = itemView.findViewById(R.id.card_view)


}