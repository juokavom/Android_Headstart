package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    private fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(
                    this, "The chosen date is $selectedYear-$selectedMonth-$selectedDayOfMonth",
                    Toast.LENGTH_LONG
                ).show()
                val selectedDate = "$selectedYear-${selectedMonth+1}-$selectedDayOfMonth"
                binding.tvSelectedDate.setText(selectedDate)
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateInMinutes = theDate!!.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinutes = currentDate!!.time / 60000
                val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes
                binding.tvSelectedDateInMinutes.setText(differenceInMinutes.toString())
            }, year, month, day)
        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}