package com.anhandra.happyplaces

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anhandra.happyplaces.database.PlaceDAO
import javax.sql.DataSource

class ListPlacesViewModelFactory(
    private val dataSource: PlaceDAO,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListPlacesViewModel::class.java)) {
            return ListPlacesViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}