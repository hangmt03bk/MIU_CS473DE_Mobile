package com.cs473.foodiepal.mealplanner

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener
import com.cs473.foodiepal.R
import com.cs473.foodiepal.databinding.FragmentMealBinding
import com.cs473.foodiepal.recipes.UserData
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class MealFragment : Fragment(), OnDayClickListener, OnSelectDateListener, MealDialog.onSavePlanListener  {
    private lateinit var binding: FragmentMealBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_meal, container, false)
        binding = FragmentMealBinding.bind(view)

        binding.calendarView.setOnDayClickListener(this)
        binding.calendarView.setSelectionBackground(R.drawable.meal)
        binding.btnMakePlan.setOnClickListener{onMakePlan()}
        updateCalendar()
        updateSelectedToday(Date())

        return binding.root
    }

    private fun updateCalendar() {
        var mealDates: MutableList<EventDay> = ArrayList()

        UserData.user.mealPlans.forEach{
            val cal = Calendar.getInstance()
            cal.time = it.date
            mealDates.add(EventDay(cal, R.drawable.recipes))
        }
        binding.calendarView.setEvents(mealDates)
    }

    private fun updateMealPlanned(date: Date) {
        val selectedPlan = UserData.user.mealPlans.find{it.date == date}
        updateMenu(binding.tvMenuBreakfast, binding.ivMenuBreakfast,
            selectedPlan?.mealID?.get(0) ?: 0
        )
        updateMenu(binding.tvMenuLunch, binding.ivMenuLunch,
            selectedPlan?.mealID?.get(1) ?: 0
        )
        updateMenu(binding.tvMenuDinner, binding.ivMenuDinner,
            selectedPlan?.mealID?.get(2) ?: 0
        )
    }

    private fun updateMenu(tv: TextView, im: ImageView, mealID: Int){
        val recipe = UserData.user.recipes.find{it.id == mealID}
        tv.text = ""
        im.setImageResource(0)
        if(recipe == null)
            return
        tv.text = recipe.name
        im.setImageResource(recipe.imgId)
    }

    private fun updateSelectedToday(date: Date){
        val currentDayString = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(date)
        binding.tvSelectedDay.text = currentDayString

        updateMealPlanned(Date(currentDayString))
    }

    private fun onMakePlan() {
        val date = Date(binding.tvSelectedDay.text.toString())
        var selectedPlan = UserData.user.mealPlans.find { it.date == Date(binding.tvSelectedDay.text.toString())}
        if(selectedPlan == null)
            selectedPlan = MealPlan(date, mutableListOf())

        val dialog = MealDialog(selectedPlan)
        dialog.setTargetFragment(this,0)
        dialog.show(parentFragmentManager, MealDialog::class.java.name)

    }

    override fun onDayClick(eventDay: EventDay) {
        updateSelectedToday(eventDay.calendar.time)
        //binding.calendarView.setDate(eventDay.calendar)
        binding.calendarView.setHighlightedDays(mutableListOf(eventDay.calendar))

    }

    override fun onSelect(calendar: List<Calendar>) {
        // open AddNoteActivity
        //val num = calendar.size
    }

    override fun onSavePlan(planDate: Date) {
        updateSelectedToday(planDate)
        val cal = Calendar.getInstance()
        cal.time = planDate
        binding.calendarView.setHighlightedDays(mutableListOf(cal))
    }
}