package com.cs473.foodiepal.blog

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.cs473.foodiepal.R
import com.cs473.foodiepal.databinding.DialogBlogBinding
import com.cs473.foodiepal.databinding.DialogMealBinding
import com.cs473.foodiepal.databinding.DialogRecipeBinding
import com.cs473.foodiepal.recipes.RecipesDialog
import com.cs473.foodiepal.recipes.UserData
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class BlogDialog() : DialogFragment() {

    interface OnSaveBlogListener {
        fun onSaveBlog(blog: Blog)
    }

    private lateinit var binding: DialogBlogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Create a new dialog
        val dialog = super.onCreateDialog(savedInstanceState)

        binding = DialogBlogBinding.inflate(layoutInflater)
        // Set the content view
        dialog.setContentView(binding.root)

        // Calculate the width as 70% of the parent width
        val parentWidth = resources.displayMetrics.widthPixels
        val width = (parentWidth * 0.9).toInt()

        // Set the width and height of the dialog
        dialog.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        binding.btnSave.setOnClickListener { onSave() }
        binding.btnCancel.setOnClickListener { onCancel() }

        return dialog
    }

    private fun onCancel() {
        dismiss()
    }

    private fun onSaveValid(): Boolean {
        if (binding.edtBlogName.text.isEmpty()) {
            Toast.makeText(context, "Blog name is empty!!!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding.edtBlogDesc.text.isEmpty()) {
            Toast.makeText(context, "Blog description is empty!!!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (binding.edtBlogUrl.text.isEmpty()) {
            Toast.makeText(context, "Blog URL is empty!!!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun onSave() {
        if (!onSaveValid()) return
        val blogName = binding.edtBlogName.text.toString()
        val blogDesc = binding.edtBlogDesc.text.toString()
        val blogUrl = binding.edtBlogUrl.text.toString()

        val listener = targetFragment as? OnSaveBlogListener
        listener?.onSaveBlog(Blog(blogName,blogDesc,blogUrl))

        dismiss()
    }

}