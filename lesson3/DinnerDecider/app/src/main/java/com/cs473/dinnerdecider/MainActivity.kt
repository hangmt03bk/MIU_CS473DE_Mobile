package com.cs473.dinnerdecider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var foods = arrayOf("Hamburger", "Pizza", "Mexican", "American", "Chinese")
    lateinit var txtFood: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtFood = findViewById(R.id.txtFood)
        findViewById<Button>(R.id.btnAddFood).setOnClickListener{addFood()}
        findViewById<Button>(R.id.btnDecide).setOnClickListener{decideFood()}
    }

    fun addFood(){
        val newFood: String = findViewById<EditText>(R.id.edtFood).text.toString()
        if(!foods.contains(newFood)){
            foods += newFood
        }
    }

    fun decideFood(){
        txtFood.text = foods[Random.nextInt(0,foods.size)]
    }
}