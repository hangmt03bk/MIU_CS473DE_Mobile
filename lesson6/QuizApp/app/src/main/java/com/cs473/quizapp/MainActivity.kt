package com.cs473.quizapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.cs473.quizapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReset.setOnClickListener{onResetClick()}
        binding.btnSubmit.setOnClickListener{onSubmitClick()}
    }

    private fun onResetClick(){
        binding.groupAns1.clearCheck();

        binding.chkQ2Ans1.isChecked = false
        binding.chkQ2Ans2.isChecked = false
        binding.chkQ2Ans3.isChecked = false
        binding.chkQ2Ans4.isChecked = false
    }

    private fun onSubmitClick(){
        var point = 0
        if(binding.rdQ1Ans1.isChecked)
            point += 50
        if(binding.chkQ2Ans1.isChecked && binding.chkQ2Ans2.isChecked)
            point += 50

        val time = Calendar.getInstance().time
        val current = SimpleDateFormat("yyyy-MM-dd HH:mm").format(time)
        showResult("Quiz Result", "Congratulation! You submitted on the current $current, you achieved $point%")
    }

    private fun showResult(title:String, msg: String){
        val builder = AlertDialog.Builder(this)
        builder.setMessage(msg)
        builder.setTitle(title)
        builder.setPositiveButton("OK") { dlgInterface, _ ->
            onResetClick()
            dlgInterface.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}