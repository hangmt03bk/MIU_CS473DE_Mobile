package com.cs473.gardening.ui.GardenLog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cs473.gardening.databinding.FragmentGardenLogBinding
import com.cs473.gardening.db.Plant
import com.cs473.gardening.ui.BaseFragment

class GardenLogFragment : BaseFragment(), PlantAdapter.OnItemClick,
    AddPlantDialog.OnAddPlantListener {
    private lateinit var binding: FragmentGardenLogBinding
    private lateinit var viewModel: GardenLogViewModel
    private lateinit var adapter: PlantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGardenLogBinding.inflate(inflater, container, false)

        binding.fabAdd.setOnClickListener { onAddPlant() }
        binding.rvPlants.layoutManager = LinearLayoutManager(requireContext())
        adapter = PlantAdapter(this@GardenLogFragment)

        viewModel = ViewModelProvider(this)[GardenLogViewModel::class.java]
        createDB()

        viewModel.allPlants.observe(viewLifecycleOwner) {
            adapter.setData(it)
            binding.rvPlants.adapter = adapter

        }

        return binding.root
    }

    override fun onItemClickListener(plantID: Int) {
        val direction =
            com.cs473.gardening.ui.GardenLog.GardenLogFragmentDirections.actionGardenLogToPlantDetails(
                plantID
            )
        findNavController().navigate(direction)
    }

    private fun onAddPlant() {
        val dialog = AddPlantDialog(this)
        dialog.show(parentFragmentManager, AddPlantDialog::class.java.name)
        /*val direction =
            com.cs473.gardening.ui.GardenLog.GardenLogFragmentDirections.actionGardenLogToAddPlantFragment()
        findNavController().navigate(direction)*/
    }

    private fun createDB() {
        viewModel.allPlants.value?.let {
            if (it.isNotEmpty())
                return
            //viewModel.deleteAll()
            viewModel.insert(Plant("Rose", "Flower", 2, "2023-01-01"))
            viewModel.insert(Plant("Tomato", "Vegetable", 3, "2023-02-15"))
            viewModel.insert(Plant("Basil", "Herb", 1, "2023-03-10"))
        }
    }

    override fun onAddPlant(plant: Plant) {
        viewModel.insert(plant)
    }
}