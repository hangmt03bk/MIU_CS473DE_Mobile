package com.cs473.foodiepal.aboutme

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.cs473.foodiepal.databinding.DialogAboutMeBinding



class AboutMeDialog: DialogFragment() {
    interface OnAddDetailListener {
        fun onDetailAdd(detail: String)
        fun onDetailRemove()
    }
    private lateinit var binding: DialogAboutMeBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Create a new dialog
        val dialog = super.onCreateDialog(savedInstanceState)

        binding = DialogAboutMeBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        // Calculate the width as 70% of the parent width
        val parentWidth = resources.displayMetrics.widthPixels
        val width = (parentWidth * 0.9).toInt()

        // Set the width and height of the dialog
        dialog.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        binding.btnAdd.setOnClickListener{onAdd()}
        binding.btnCancel.setOnClickListener{onCancel()}

        return dialog
    }

    private fun onCancel() {
        dismiss()
    }

    private fun onAdd() {

        val listener = targetFragment  as? OnAddDetailListener
        listener?.onDetailAdd(binding.edtNewDetail.text.toString())
         dismiss()
    }

}