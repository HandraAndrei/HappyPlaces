package com.anhandra.happyplaces

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.anhandra.happyplaces.database.PLACE_ID_EXTRA
import com.anhandra.happyplaces.database.Place
import com.anhandra.happyplaces.database.PlacesDatabase
import com.anhandra.happyplaces.databinding.FragmentListBinding


class ListFragment : Fragment(), PlaceClickListener {

    private lateinit var listPlacesViewModel: ListPlacesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentListBinding>(
            inflater,
            R.layout.fragment_list,
            container,
            false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = PlacesDatabase.getInstance(application).placeDatabaseDao
        val viewModelFactory = ListPlacesViewModelFactory(dataSource, application)

        listPlacesViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(ListPlacesViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.listPlacesViewModel = listPlacesViewModel

        binding.addButton.setOnClickListener {
            it.findNavController().navigate(R.id.addNewPlaceFragment)
        }

        val adapter = PlaceAdapter(this)
        binding.placesList.adapter = adapter

        listPlacesViewModel.places.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        return binding.root
    }

    override fun onClick(place: Place) {
        val intent = Intent(context, DetailsActivity::class.java)
        val places = listPlacesViewModel.places.value
        if (places != null) {
            for (p in places) {
                if (place.placeId == p.placeId) {
                    intent.putExtra(PLACE_ID_EXTRA, place.placeId)
                    intent.putExtra("name", place.placeName)
                    intent.putExtra("location", place.location)
                    intent.putExtra("date", place.date)
                    intent.putExtra("mood", place.mood)
                    intent.putExtra("note", place.personalNote)
                    intent.putExtra("activity", place.activityType)
                    intent.putExtra("placeType", place.placeType)
                    startActivity(intent)
                }
            }
        }

    }

    override fun onLongClick(place: Place): Boolean {
        view?.findNavController()?.navigate(R.id.editPlaceFragment)
        return true
    }
}