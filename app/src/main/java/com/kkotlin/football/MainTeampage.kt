package com.kkotlin.football

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.hwido.football.R
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener
import java.util.Calendar

class MainTeampage : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_teampage)

        Log.d("activity onCreate", "activity onCreate")

        val startDate = Calendar.getInstance()
        startDate.add(Calendar.MONTH, -1)

        val endDate = Calendar.getInstance()
        endDate.add(Calendar.MONTH, 1)

        val horizontalCalendar = HorizontalCalendar.Builder(
            this,
            R.id.main_teampage_calendar_singlerow
        )
            .range(startDate, endDate)
            .datesNumberOnScreen(7)
            .build()

        horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
            override fun onDateSelected(date: Calendar, position: Int) {
                Log.e("TAG", "CURRENT DATE IS $date")
            }
        }



        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
//        fragmentTransaction.add(R.id.main_teampage_fragment, MainTeampageFragmentSchedule())
//        fragmentTransaction.commit()
//          추후 fragment로 나눠어서 관리



        //하단버튼
        val mainBtnmain = findViewById<Button>(R.id.main_button_main)
        val mainBtnIndividual = findViewById<Button>(R.id.main_button_individual)
        val mainBtnSetting = findViewById<Button>(R.id.main_button_setting)

        mainBtnmain.setOnClickListener {
            val intent = Intent(baseContext, MainMainpage::class.java)
            startActivity(intent)
        }

        mainBtnIndividual.setOnClickListener {
            //
        }

        mainBtnSetting.setOnClickListener {
            val intent = Intent(baseContext, MainPersonalInformationpage::class.java)
            startActivity(intent)
        }


    }


}