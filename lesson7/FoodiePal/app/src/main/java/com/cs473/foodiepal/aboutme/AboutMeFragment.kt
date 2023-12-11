package com.cs473.foodiepal.aboutme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cs473.foodiepal.R
import com.cs473.foodiepal.databinding.FragmentAboutMeBinding
import com.cs473.foodiepal.recipes.RecipesDialog
import com.cs473.foodiepal.recipes.UserData
import com.google.android.material.snackbar.Snackbar

class AboutMeFragment : Fragment(), AboutMeDialog.OnAddDetailListener {
    private lateinit var tvNewDetail:TextView
    private lateinit var binding: FragmentAboutMeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about_me, container, false)
        binding = FragmentAboutMeBinding.bind(view)

        binding.ivMe.setImageResource(UserData.user.aboutMe.imgId)
        binding.tvName.text = UserData.user.aboutMe.name
        binding.fabAboutMeAdd.setOnClickListener {onAboutMeAdd()}
        viewDetails()
        return binding.root
    }

    private fun viewDetails(){
        for(i in 1..UserData.user.aboutMe.details.size) {
            onAddDetail(UserData.user.aboutMe.details[i-1])
        }
    }

    private fun onAddDetail(detail: String) {
        tvNewDetail = TextView(context)
        tvNewDetail.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        tvNewDetail.text = "   $detail\n"
        tvNewDetail.textSize = 18f // Set text size as needed
        binding.llAmDetails.addView(tvNewDetail)
    }

    override fun onDetailAdd(detail: String) {
        UserData.user.aboutMe.details.add(detail)
        onAddDetail(detail)
        //Toast.makeText(context, "New detail is added", Toast.LENGTH_SHORT).show()
        Snackbar.make(binding.root, "New detail is added", Snackbar.LENGTH_SHORT)
            .setAction("Undo", View.OnClickListener {view ->
                onDetailRemove()
                Snackbar.make(view, "New detail is removed", Snackbar.LENGTH_SHORT).show()
            }).show()
    }

    override fun onDetailRemove() {
        UserData.user.aboutMe.details.removeAt(UserData.user.aboutMe.details.size-1)
        binding.llAmDetails.removeView(tvNewDetail)
    }

    private fun onAboutMeAdd() {
        val dialog = AboutMeDialog()
        dialog.setTargetFragment(this,0)
        dialog.show(parentFragmentManager, AboutMeDialog::class.java.name)
    }
}