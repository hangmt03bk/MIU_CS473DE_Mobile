package com.cs473.gardening.ui.GardenLog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cs473.gardening.databinding.FragmentPlantItemBinding
import com.cs473.gardening.db.Plant

class PlantAdapter(private val plantItemCb: OnItemClick): RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {
    private var plants: MutableList<Plant> = mutableListOf()

    interface OnItemClick{
        fun onItemClickListener(plantID: Int)
    }
    private lateinit var binding: FragmentPlantItemBinding

    class PlantViewHolder(private val viewBinding: FragmentPlantItemBinding) : RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlantViewHolder {
        binding = FragmentPlantItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        binding.tvName.text = plants[position].name
        binding.cvPlantItem.setOnClickListener{
            plantItemCb.onItemClickListener(plants[position].id)
        }
    }

    override fun getItemCount(): Int = plants.size

    fun setData(newPlants: List<Plant>) {
        plants.clear()
        plants.addAll(newPlants)
        notifyDataSetChanged()
    }
}