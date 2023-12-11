package com.cs473.foodiepal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.cs473.foodiepal.blog.BlogFragment
import com.cs473.foodiepal.databinding.ActivityHomeBinding
import com.cs473.foodiepal.databinding.ActivityMainBinding
import com.cs473.foodiepal.mealplanner.MealFragment
import com.cs473.foodiepal.recipes.RecipesDialog
import com.cs473.foodiepal.recipes.RecipesFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTabs()
        initBottomMenu()
    }

    private fun initTabs(){
        binding.vp2.adapter = PageAdapter(this)
        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        TabLayoutMediator(binding.tabLayout, binding.vp2) { tab, position ->
            when (position) {
                0 -> {tab.text = resources.getString(R.string.menu_recipes)
                    tab.setIcon(R.drawable.recipes)
                }
                1 -> {tab.text = resources.getString(R.string.menu_meal)
                    tab.setIcon(android.R.drawable.ic_dialog_dialer)
                }
                2 -> {tab.text = resources.getString(R.string.menu_blog)
                    tab.setIcon(android.R.drawable.stat_notify_chat)
                }
                3 -> {tab.text = resources.getString(R.string.menu_contact)
                    tab.setIcon(android.R.drawable.ic_dialog_email)
                }
                4 -> {tab.text = resources.getString(R.string.menu_about_me)
                    tab.setIcon(android.R.drawable.ic_dialog_info)
                }
            }
        }.attach()
    }

    private fun initBottomMenu() {
        binding.btNavView.setOnItemSelectedListener{
            when(it.itemId) {
                R.id.menu_recipes -> binding.vp2.currentItem = 0
                R.id.menu_mealPlanner -> binding.vp2.currentItem = 1
                R.id.menu_blog -> binding.vp2.currentItem = 2
            }
            true
        }
    }

   /*private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.vp2, fragment)
            commit()
        }
    }*/
}