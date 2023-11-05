package com.cs473.walmart

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ShareCompat
import com.cs473.walmart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var userList: ArrayList<User> = ArrayList<User>()
    private lateinit var resultCreateContracts: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addUserData()

        binding.btnSignin.setOnClickListener{clickSignin()}
        binding.btnCreate.setOnClickListener{clickCreate()}
        binding.tvForgotPsw.setOnClickListener{clickForgotPsw()}

        resultCreateContracts =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if(result.resultCode == Activity.RESULT_OK){
                    //val newUser = result.data?data.toString()
                    val newUser = result.data?.getSerializableExtra("user") as? User
                    if(newUser != null)
                        userList.add(newUser)
                }
            }
    }

    private fun addUserData(){
        userList.add(User("Hang","Nguyen","thi.nguyen@miu.edu","1234"))
        userList.add(User("Leo","Nguyen","leo.nguyen@miu.edu","1234"))
        userList.add(User("Lam","Nguyen","lam.nguyen@miu.edu","1234"))
        userList.add(User("Bill","Gate","bill.gate@miu.edu","1234"))
        userList.add(User("Steve","Job","steve.job@miu.edu","1234"))
    }

    private fun isValidUser(username: String, password: String): Boolean{
        return userList.any { it.username == username && it.password == password }
    }
    private fun clickSignin() {
        if(!isValidUser(binding.edtEmail.text.toString(), binding.edtPassword.text.toString())){
            Toast.makeText(this, "Non-existed username or incorrect password.", Toast.LENGTH_SHORT).show()
            return
        }

        var intent = Intent(this, ShoppingCategoryActivity::class.java)
        intent.putExtra("username", binding.edtEmail.text.toString());
        startActivity(intent)
    }

    private fun clickCreate() {
        var intent = Intent(this, RegisterActivity::class.java)
        resultCreateContracts.launch(intent)
    }

    private fun clickForgotPsw() {
        val username = binding.edtEmail.text.toString()
        val user = userList.find{ it.username == username }
        if(user == null)
            Toast.makeText(this, "Non-existed username", Toast.LENGTH_SHORT).show()
        else {
/*
            ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setChooserTitle("Send password with: ")
                .setText(user.password)
                .startChooser()

 */

            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            //intent.data = Uri.parse("mailTo:")
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_EMAIL, user.username)
            intent.putExtra(Intent.EXTRA_SUBJECT, "Password from Walmart")
            intent.putExtra(Intent.EXTRA_TEXT, "Your password is ${user.password}")
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }


        }
    }
}