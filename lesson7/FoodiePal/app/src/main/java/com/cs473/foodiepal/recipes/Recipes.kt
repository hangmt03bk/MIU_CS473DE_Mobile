package com.cs473.foodiepal.recipes

data class Recipes(val id: Int, val name: String,
                   val ingredients: String, val cookingTime: Int,
                   val rating: Float, val imgId: Int) {
}