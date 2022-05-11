package com.anhandra.happyplaces

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anhandra.happyplaces.database.Place
import com.anhandra.happyplaces.database.PlaceDAO
import kotlinx.coroutines.launch

class ListPlacesViewModel(
    val database: PlaceDAO,
    application: Application
) : AndroidViewModel(application) {

    private var lastPlace = MutableLiveData<Place?>()
    val places = database.getAllPlaces()


}