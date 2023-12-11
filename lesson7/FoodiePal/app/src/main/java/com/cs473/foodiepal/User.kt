package com.cs473.foodiepal

import com.cs473.foodiepal.aboutme.AboutMe
import com.cs473.foodiepal.blog.Blog
import com.cs473.foodiepal.mealplanner.MealPlan
import com.cs473.foodiepal.recipes.Recipes

data class User(val username: String, val password: String,
                val recipes: MutableList<Recipes>,
                val mealPlans: MutableList<MealPlan>,
                val blogs: MutableList<Blog>,
                val aboutMe: AboutMe)