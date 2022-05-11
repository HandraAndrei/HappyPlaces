package com.anhandra.happyplaces.listeners

import com.anhandra.happyplaces.database.Place

interface PlaceClickListener {

    fun onClick(place: Place)

    fun onLongClick(place: Place): Boolean
}