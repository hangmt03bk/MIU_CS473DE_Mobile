package com.cs473.foodiepal.recipes

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cs473.foodiepal.databinding.FragmentRecipeBinding


class RecipesAdapter(private var recipes: MutableList<Recipes>) : RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {
    private lateinit var binding: FragmentRecipeBinding
    inner class RecipesViewHolder(recipesView: FragmentRecipeBinding): RecyclerView.ViewHolder(recipesView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        binding = FragmentRecipeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecipesViewHolder(binding)
    }

    override fun getItemCount() = recipes.size

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        with(holder) {
            with(recipes[position]) {
                binding.ivRecipe.setImageResource(this.imgId)
                binding.tvName.text = this.name
                binding.tvIngd.text = "with ${this.ingredients}"
                binding.tvCookingTime.text = "${this.cookingTime} mins"
                binding.ratingBar.rating = this.rating

                Log.i("[Hang] Recipe ", "id ${this.id}")
            }
        }
    }
}