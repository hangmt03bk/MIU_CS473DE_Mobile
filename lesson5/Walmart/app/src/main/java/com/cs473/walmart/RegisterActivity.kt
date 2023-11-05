package com.cs473.walmart

import android.app.Activity
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cs473.walmart.databinding.ActivityMainBinding
import com.cs473.walmart.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreate.setOnClickListener{clickCreate()}
    }

    private fun clickCreate() {
        Toast.makeText(this, "Account created successfully", Toast.LENGTH_LONG).show()
        val fn = binding.edtFirstName.text.toString()
        val ln = binding.edtLastName.text.toString()
        val email = binding.edtEmail.text.toString()
        val psw = binding.edtPassword.text.toString()
        val newUser = User(fn,ln,email,psw)

        //intent.data = Uri.parse(newUser.toString())
        intent.putExtra("user", newUser)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}