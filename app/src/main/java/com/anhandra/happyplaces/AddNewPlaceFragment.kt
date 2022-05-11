package com.anhandra.happyplaces

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isEmpty
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.anhandra.happyplaces.database.Place
import com.anhandra.happyplaces.database.PlacesDatabase
import com.anhandra.happyplaces.databinding.FragmentAddNewPlaceBinding
import java.util.*


class AddNewPlaceFragment : Fragment(), AdapterView.OnItemSelectedListener {

    lateinit var binding: FragmentAddNewPlaceBinding
    var activityText: String = "default"
    var placeText: String = "default"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_new_place, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = PlacesDatabase.getInstance(application).placeDatabaseDao
        val viewModelFactory = AddNewPlaceViewModelFactory(dataSource, application)

        val addNewPlaceViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(AddNewPlaceViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.addNewPlaceViewModel = addNewPlaceViewModel



        binding.selectDateButton.setOnClickListener {
            showDatePicker(binding.selectedDate, context)
        }

        populateActivityPicker(binding.activitySpinner, context)
        populatePlacePicker(binding.placeSpinner, context)

        binding.saveButton.setOnClickListener {
            preparePlace(addNewPlaceViewModel)
            addNewPlaceViewModel.insertPlace()
            Toast.makeText(context, "Place added successfully", Toast.LENGTH_SHORT).show()
            this.findNavController().navigate(R.id.listFragment)
        }


        return binding.root
    }

    fun preparePlace(viewModel: AddNewPlaceViewModel) {
        if (binding.enterPlaceNameText.text.toString()
                .isEmpty() || binding.enterLocationText.text.toString()
                .isEmpty() || binding.enterNoteText.text.toString().isEmpty()
            || binding.selectedDate.text.toString().isEmpty()
        ) {
            Toast.makeText(context, "Please enter all the fields", Toast.LENGTH_SHORT).show()
        } else {
            val place = Place()
            place.placeName = binding.enterPlaceNameText.text.toString()
            place.date = binding.selectedDate.text.toString()
            place.personalNote = binding.enterNoteText.text.toString()
            place.mood = binding.moodSlider.value.toInt()
            place.activityType = activityText
            place.placeType = placeText
            place.location = binding.enterLocationText.text.toString()
            viewModel.place = place
        }
    }

    private fun populatePlacePicker(spinner: Spinner, context: Context?) {
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.places_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
                spinner.onItemSelectedListener = this
            }
        }
    }

    private fun populateActivityPicker(spinner: Spinner, context: Context?) {
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.activities_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
                spinner.onItemSelectedListener = this
            }
        }
    }

    private fun showDatePicker(textView: TextView, context: Context?) {

        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        val datePicker =
            context?.let {
                DatePickerDialog(
                    it,
                    DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        textView.text = "$dayOfMonth/$month /$year"
                    },
                    year,
                    month,
                    day
                )
            }
        datePicker?.show()
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (p0?.id == R.id.activity_spinner) {
            val text: String = p0.getItemAtPosition(p2).toString()
            activityText = text
        } else if (p0?.id == R.id.place_spinner) {
            val text: String = p0.getItemAtPosition(p2).toString()
            placeText = text
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}