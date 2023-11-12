package com.cs473.electronics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.cs473.electronics.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ProductInterface {
    private lateinit var binding: ActivityMainBinding
    private var myCart = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnViewCart.setOnClickListener{onViewCartClick()}

        binding.rcvProducts.layoutManager = LinearLayoutManager(this)
        binding.rcvProducts.adapter = ItemListAdapter(this, this, getProducts())

    }

    private fun getProducts(): ArrayList<Product> {
        val products = ArrayList<Product>()
        products.add(Product("Fancy Cat", "Small, lazy cat with black ears", 200.0f, R.drawable.cat1))
        products.add(Product("Kid T-Shirt", "Spider, red nice T-Shirt for boys", 10.0f, R.drawable.clothes1))
        products.add(Product("Smart Watch", "Full Touch Screen Fitness", 159.9f, R.drawable.watch1))
        products.add(Product("Granola bars", "Chocolate Chip, Peanut Butter", 12.9f, R.drawable.food1))
        products.add(Product("Apple Ipad", "Apple iPad (9th Generation)", 249.0f, R.drawable.ipad))
        products.add(Product("MacBook Air", "Apple MacBook Air Laptop M1 Chip", 849.0f, R.drawable.macbook))
        return products
    }

    private fun onViewCartClick(){
        var cartItems = ArrayList<String>()
        myCart.forEach {cartItems += it.name}
        Toast.makeText(this, "You have $cartItems in your cart", Toast.LENGTH_LONG).show()
    }

    override fun onItemClick(p: Product) {
        myCart.add(p)
        Toast.makeText(this,"${p.name} is added to cart", Toast.LENGTH_SHORT).show()
    }
}