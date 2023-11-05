package com.cs473.walmart

import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cs473.walmart.databinding.ActivityShoppingCategoryBinding

class ShoppingCategoryActivity : AppCompatActivity() {
    private lateinit var categories: Array<String>
    private val numPerRow = 2
    private lateinit var binding: ActivityShoppingCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username: String? = intent.getStringExtra("username")
        binding.tvWelcome.text = "Welcome $username"

        categories = resources.getStringArray(R.array.categories)
        addCategories()
    }

    private fun addCategories() {
        for(i in 0..categories.size step numPerRow) {
            addCatRow( i, (i + numPerRow - 1).coerceAtMost(categories.size-1))
        }
  }

    private fun addCatRow( start: Int, end: Int) {
        val tableRow = TableRow(applicationContext)
        tableRow.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT)
        for(i in start..end)
            tableRow.addView(getCatRowCell(categories[i]))

        binding.tblCate.addView(tableRow) // id from Layout_file
    }

    private fun getCatRowCell(cate: String): View {
        val cellLayout = LinearLayout(this)
        cellLayout.layoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT,
            1f
        ).apply {
            setMargins(25, 0, 8, 25)
        }
        cellLayout.orientation = LinearLayout.VERTICAL

        // Create an ImageView
        val imageView = ImageView(this)
        val resourceId = resources.getIdentifier(cate.lowercase(), "drawable", packageName)
        imageView.setImageResource(resourceId)
        val imageParams =LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            400)
        imageParams.setMargins(16, 16, 16, 16)
        imageView.layoutParams = imageParams
        imageView.setOnClickListener {
            Toast.makeText(this, "You have chosen the $cate category for shopping", Toast.LENGTH_LONG).show()
        }

        cellLayout.addView(imageView)

        // Create a TextView
        val textView = TextView(this)
        textView.text = cate
        val textParams =LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        textParams.setMargins(30, 16, 16, 16)
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        textView.layoutParams = textParams

        cellLayout.addView(textView)

        return cellLayout
    }

}