package com.cs473.foodiepal

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.cs473.foodiepal.aboutme.AboutMe
import com.cs473.foodiepal.blog.Blog
import com.cs473.foodiepal.databinding.ActivityMainBinding
import com.cs473.foodiepal.mealplanner.MealPlan
import com.cs473.foodiepal.recipes.Recipes
import com.cs473.foodiepal.recipes.UserData
import java.util.Date

class MainActivity : AppCompatActivity() {
    private val users = mutableListOf<User>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var spf: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{onLogin()}
        initUsers()
        initUI()
/*
        val strings = arrayOf("str1", "str2", "str3")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,strings)
        val actv = AutoCompleteTextView(this)
        actv.setAdapter(adapter)
        actv.threshold = 1
        actv.onItemClickListener = AdapterView.OnItemClickListener{parent, view, position,id ->
            Toast.makeText(this, "Item " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show()
        }

 */
    }

    private fun initUsers() {
        val user = User("hang@miu.edu", "1234",
            initRecipes(),
            initMealPlans(),
            initBlogs(),
            initUserAboutMe())

        users.add(user)
    }

    private fun initRecipes(): MutableList<Recipes> {
        val recipes: MutableList<Recipes> = mutableListOf()
        for(i in 1.. 4){
            val name = getString(resources.getIdentifier("rcp${i}_name", "string", packageName))
            val ingredient = getString(resources.getIdentifier("rcp${i}_ingredient", "string", packageName))
            val cookingTime = getString(resources.getIdentifier("rcp${i}_cookingTime", "string", packageName))
            val rating = getString(resources.getIdentifier("rcp${i}_rating", "string", packageName))
            val imgId = resources.getIdentifier("rcp${i}", "drawable", packageName)
            recipes.add(Recipes(i,name,ingredient, cookingTime.toInt(),rating.toFloat(), imgId))
        }
        return recipes
    }
    private fun initUserAboutMe(): AboutMe {
        val am = AboutMe(resources.getString(R.string.am_name), R.drawable.me, mutableListOf() )
        for(i in 1..2) {
            am.details.add(getString(resources.getIdentifier("am_detail${i}", "string", packageName)))
        }
        return am
    }

    private fun initBlogs(): MutableList<Blog> {
        val blogs: MutableList<Blog> = mutableListOf()
        for(i in 1.. 2){
            val name = getString(resources.getIdentifier("blog_name${i}", "string", packageName))
            val desc = getString(resources.getIdentifier("blog_desc${i}", "string", packageName))
            val url = getString(resources.getIdentifier("blog_url${i}", "string", packageName))
            blogs.add(Blog(name,desc,url))
        }
        return blogs
    }

    private fun initMealPlans(): MutableList<MealPlan> {
        val plan: MutableList<MealPlan> = mutableListOf()
        plan.add(MealPlan(Date("12/08/2023"), mutableListOf(1, 2, 3)))
        plan.add(MealPlan(Date("12/09/2023"), mutableListOf(4, 2, 5)))
        return plan
    }
    private fun initUI() {
        spf = getSharedPreferences("login", Context.MODE_PRIVATE)
        binding.edtEmail.setText(spf.getString("username",""))
        binding.edtPassword.setText(spf.getString("password",""))
    }

    private fun onLogin() {
        val username = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()
        val user = users.find { it.username == username && it.password == password }
        if(user == null) {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            return;
        }

        UserData.user = user
        updateSharedPreference()
        goHomeActivity()
    }

    private fun updateSharedPreference() {
        val edit = spf.edit()
        edit.putString("username", binding.edtEmail.text.toString())
        edit.putString("password", binding.edtPassword.text.toString())
        edit.apply()
    }

    private fun goHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}