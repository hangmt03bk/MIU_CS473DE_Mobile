package com.cs473.gardening.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.cs473.gardening.R
import com.cs473.gardening.db.Plant
import com.cs473.gardening.db.PlantDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var navCtrl: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navCtrl = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navCtrl)

        //createDB()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navCtrl.navigateUp()
    }

    private fun createDB() {
        lifecycleScope.launch(Dispatchers.IO) {
            val dao = PlantDatabase(applicationContext).getPlantDao()
            dao.deleteAll()
            dao.insert(Plant("Rose", "Flower", 2, "2023-01-01"))
            dao.insert(Plant("Tomato", "Vegetable", 3, "2023-02-15"))
            dao.insert(Plant("Basil", "Herb",  1, "2023-03-10"))

        }
    }
}