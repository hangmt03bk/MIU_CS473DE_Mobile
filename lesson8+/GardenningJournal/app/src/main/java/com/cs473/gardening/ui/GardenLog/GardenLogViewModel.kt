package com.cs473.gardening.ui.GardenLog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cs473.gardening.db.Plant
import com.cs473.gardening.db.PlantDao
import com.cs473.gardening.db.PlantDatabase
import kotlinx.coroutines.launch

class GardenLogViewModel(app: Application) : AndroidViewModel(app) {
    private val plantDao: PlantDao
    val allPlants: LiveData<List<Plant>>

    init {
        plantDao = PlantDatabase(app).getPlantDao()
        allPlants = plantDao.getAllPlants()
    }

    fun insert(plant: Plant) = viewModelScope.launch {
        plantDao.insert(plant)
    }

    fun update(plant: Plant) = viewModelScope.launch {
        plantDao.update(plant)
    }

    fun deleteAll() = viewModelScope.launch {
        plantDao.deleteAll()
    }
}