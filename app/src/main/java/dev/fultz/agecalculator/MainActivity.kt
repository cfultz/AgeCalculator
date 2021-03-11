package dev.fultz.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view : View){

        val ageCalendar = Calendar.getInstance()
        val year = ageCalendar.get(Calendar.YEAR)
        val month = ageCalendar.get(Calendar.MONTH)
        val day = ageCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, selectedYear, selectedMonth, selectedDay ->

            val selectedDate = "${selectedMonth + 1}/$selectedDay/$selectedYear"

            selectedDateTV.setText(selectedDate)
            //Setup the formatting for US locale
            val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.US)
            val theDate = sdf.parse(selectedDate)
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            // Days
            val selectedDateInDays = theDate!!.time / 84600000
            val currentDateToDays = currentDate!!.time / 84600000
            val differenceInDays = currentDateToDays - selectedDateInDays
            //Days
            val selectedDateInMonths = theDate!!.time/2629800000
            val currentDateToMonths = currentDate!!.time / 2629800000
            val differenceInMonths = currentDateToMonths - selectedDateInMonths
            //Years
            val selectedDateInYears = theDate!!.time/31557600000
            val currentDateToYears = currentDate!!.time /31557600000
            val differenceInYears = currentDateToYears - selectedDateInYears

            inDaysTV.setText(differenceInDays.toString())
            inMonthsTV.setText(differenceInMonths.toString())
            inYearsTV.setText(differenceInYears.toString())


        }, year, month, day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

    }
}