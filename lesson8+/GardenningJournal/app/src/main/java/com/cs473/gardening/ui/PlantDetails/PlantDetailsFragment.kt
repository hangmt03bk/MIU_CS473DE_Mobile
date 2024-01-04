package com.cs473.gardening.ui.PlantDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cs473.gardening.databinding.FragmentPlantDetailsBinding
import com.cs473.gardening.db.Plant
import com.cs473.gardening.ui.BaseFragment
import com.cs473.gardening.ui.GardenLog.GardenLogViewModel

class PlantDetailsFragment : BaseFragment() {
    private lateinit var binding: FragmentPlantDetailsBinding
    private var curPlantID: Int = 0
    private lateinit var viewModel: PlantDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[PlantDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlantDetailsBinding.inflate(inflater, container, false)
        binding.btnDelete.setOnClickListener { onDeletePlant() }

        curPlantID = arguments?.getInt("plantID") ?: 0

        viewModel.getPlantByID(curPlantID).observe(viewLifecycleOwner) {
            displayPlantDetails(it)
        }

        return binding.root
    }

    private fun displayPlantDetails(plant: Plant) {
        binding.tvPlantName.text = plant.name
        binding.tvType.text = "Type: ${plant.type}"
        binding.tvWaterFreq.text = "Watering Frequency: ${plant.wateringFrequency} hours"
        binding.tvDate.text = "Planting Date: ${plant.plantingDate}"
    }

    private fun onDeletePlant() {
        viewModel.delete(curPlantID)
        goGardenLog()
    }

    private fun goGardenLog() {
        val direction =
            com.cs473.gardening.ui.PlantDetails.PlantDetailsFragmentDirections.actionPlantDetailsToGardenLog()
        findNavController().navigate(direction)
    }
}