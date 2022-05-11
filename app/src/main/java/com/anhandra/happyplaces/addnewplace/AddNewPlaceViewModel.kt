package com.anhandra.happyplaces.addnewplace

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.anhandra.happyplaces.database.Place
import com.anhandra.happyplaces.database.PlaceDAO
import kotlinx.coroutines.launch

class AddNewPlaceViewModel(
    val dataSource: PlaceDAO,
    application: Application
) : AndroidViewModel(application) {

    val database = dataSource
    var place = Place()

    fun insertPlace() {
        viewModelScope.launch {
            database.insert(place)
        }

    }

    fun getLastPlace(): Place? {
        var p: Place? = Place()
        viewModelScope.launch {
            p = database.getLastPlace()
        }
        return p

    }

}