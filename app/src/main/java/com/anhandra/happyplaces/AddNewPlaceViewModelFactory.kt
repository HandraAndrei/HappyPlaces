package com.anhandra.happyplaces

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anhandra.happyplaces.database.PlaceDAO

class AddNewPlaceViewModelFactory(
    private val dataSource: PlaceDAO,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNewPlaceViewModel::class.java)) {
            return AddNewPlaceViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}