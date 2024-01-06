package com.cs473.gardening.ui.GardenLog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.cs473.gardening.databinding.DialogAddPlantBinding
import com.cs473.gardening.db.Plant
import com.cs473.gardening.db.PlantDatabase
import com.cs473.gardening.ui.BaseFragment
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class AddPlantDialog(val listener: OnAddPlantListener) : DialogFragment() {
    private lateinit var binding: DialogAddPlantBinding

    interface OnAddPlantListener{
        fun onAddPlant(plant: Plant)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        binding = DialogAddPlantBinding.inflate(layoutInflater)
        // Set the content view
        dialog.setContentView(binding.root)

        // Calculate the width as 70% of the parent width
        val parentWidth = resources.displayMetrics.widthPixels
        val width = (parentWidth * 0.9).toInt()

        // Set the width and height of the dialog
        dialog.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        binding.btnAdd.setOnClickListener { onAdd() }
        binding.btnCancel.setOnClickListener { onCancel() }
        initDate()

        return dialog

        /*
        binding = DialogAddPlantBinding.inflate(layoutInflater)
        val dialog = activity?.let {
            var builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
            builder.create()
        } ?: throw IllegalStateException("Error!!!")

        binding.btnAdd.setOnClickListener { onAdd() }
        binding.btnReset.setOnClickListener { onReset() }

        return dialog
         */
    }
/*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPlantBinding.inflate(inflater, container, false)
        binding.btnAdd.setOnClickListener { onAdd() }
        binding.btnReset.setOnClickListener { onReset() }

        return binding.root
    }
*/
    private fun isValidInput(edt: EditText, field: String): Boolean {
        if(edt.text.toString().isEmpty()) {
            Toast.makeText(context, "$field is empty!!!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun isValidInputs(): Boolean{
        if(!isValidInput(binding.edtNewName, "Plant Name")) return false
        if(!isValidInput(binding.edtNewType, "Plant Type")) return false
        if(!isValidInput(binding.edtNewFreq, "Watering Frequency")) return false
        //if(!isValidInput(binding.edtNewDate, "")) return false
        return true
    }
    private fun onAdd() {
        if(!isValidInputs()) return

        val name = binding.edtNewName.text.toString()
        val type = binding.edtNewType.text.toString()
        val freq = binding.edtNewFreq.text.toString().toInt()
        val date = binding.edtNewDate.text.toString()

        listener.onAddPlant(Plant(name, type, freq, date))

        dismiss()
        //goGardenLog()
    }

    private fun initDate() {
        val c = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH) + 1
        val mDay = c.get(Calendar.DAY_OF_MONTH)

        binding.edtNewDate.text = "$mMonth/$mDay/$mYear"

        binding.edtNewDate.setOnClickListener{
            val dpd = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { _ , year, monthOfYear, dayOfMonth ->
                    // Display Selected date in TextView
                    binding.edtNewDate.text = "$monthOfYear/$dayOfMonth/$year"
                },
                mYear,
                mMonth,
                mDay
            )
            dpd.show()
        }
    }

    /*
    private fun goGardenLog(){
        val direction = AddPlantDialogDirections.actionAddPlantFragmentToGardenLog()
        findNavController().navigate(direction)
    }

    private fun onReset() {
        binding.edtNewName.setText("")
        binding.edtNewType.setText("")
        binding.edtNewFreq.setText("")
        binding.edtNewDate.setText("")
    }
    */

    private fun onCancel(){
        dismiss()
    }
}