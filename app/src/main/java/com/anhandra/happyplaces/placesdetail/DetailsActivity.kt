package com.anhandra.happyplaces.placesdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.anhandra.happyplaces.R
import com.anhandra.happyplaces.database.PlacesDatabase
import com.anhandra.happyplaces.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val application = requireNotNull(this).application
        val dataSource = PlacesDatabase.getInstance(application).placeDatabaseDao
        val viewModelFactory = DetailsViewModelFactory(dataSource, application)

        val detailsViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(DetailsViewModel::class.java)

        binding.detailsViewModel = detailsViewModel


        getDetails()


    }

    private fun getDetails() {
        binding.placeNameDetail.text = intent.getStringExtra("name")
        binding.locationNameDetail.text = intent.getStringExtra("location")
        binding.activityTypeDetail.text = intent.getStringExtra("activity")
        binding.placeTypeDetail.text = intent.getStringExtra("placeType")
        binding.dateDetail.text = intent.getStringExtra("date")
        binding.noteDetail.text = intent.getStringExtra("note")
        binding.moodImageDetail.setImageResource(
            when (intent.getIntExtra("mood", -1)) {
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_0
            }
        )
    }

}