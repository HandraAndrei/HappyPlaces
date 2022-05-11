package com.anhandra.happyplaces.placesdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.anhandra.happyplaces.database.Place
import com.anhandra.happyplaces.database.PlaceDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(
    val dataSource: PlaceDAO,
    application: Application
) : AndroidViewModel(application) {


    val database = dataSource
    var place = Place()

}