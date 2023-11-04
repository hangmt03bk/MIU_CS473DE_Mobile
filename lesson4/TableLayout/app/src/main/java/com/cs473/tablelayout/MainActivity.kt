package com.cs473.tablelayout

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import com.cs473.tablelayout.R.color.green
import com.cs473.tablelayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener{btnAddClick()}
    }

    override fun onStart() {
        super.onStart()

        addRow("Version","Code Name")
        addRow("Android 8.0","Oreo")
        addRow("Android 9.0","Pie")
    }

    fun btnAddClick() {
        addRow(binding.edtVersion.text.toString(), binding.edtCode.text.toString())
        binding.edtVersion.text.clear()
        binding.edtCode.text.clear()
    }

    @SuppressLint("ResourceAsColor")
    fun getTextViewCell(text: String) : TextView{
        val tvText: TextView = TextView(applicationContext)
        tvText.text = text
        tvText.layoutParams = TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT,1f).apply {
            setMargins(0, 0, 8, 15)
        }
        tvText.setBackgroundColor(green)
        tvText.setPadding(20,0,0,0)
        tvText.setTextColor(R.color.white)
        return tvText
    }
    fun addRow(version:String, code:String) {
        val tableRow = TableRow(applicationContext)
        tableRow.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
        tableRow.addView(getTextViewCell(version))
        tableRow.addView(getTextViewCell(code))
        binding.tblLayoutMain.addView(tableRow) // id from Layout_file
    }
}