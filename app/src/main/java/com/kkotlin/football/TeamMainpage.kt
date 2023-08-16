package com.kkotlin.football

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.hwido.football.R
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener
import java.util.Calendar


class TeamMainpage : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?){
            super.onCreate(savedInstanceState)
        Log.d("frg oncreate","frg oncreate")








    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("oncreatedveiw","oncreatedview")

        /* starts before 1 month from now */
        val startDate = Calendar.getInstance()
        startDate.add(Calendar.MONTH, -1)

        /* ends after 1 month from now */
        val endDate = Calendar.getInstance()
        endDate.add(Calendar.MONTH, 1)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.team_mainpage, container, false)

        // on below line we are setting up our horizontal calendar view and passing id our calendar view to it.
        val horizontalCalendar = HorizontalCalendar.Builder(
            view, //sangjin 문제발생지점 this는 activity이나 fragment라서
            R.id.team_mainpage_calendar_singlerow
        ) // on below line we are adding a range
            // as start date and end date to our calendar.
            .range(startDate, endDate) // on below line we are providing a number of dates
            // which will be visible on the screen at a time.
            .datesNumberOnScreen(7) // at last we are calling a build method
            // to build our horizontal recycler view.
            .build()
        // on below line we are setting calendar listener to our calendar view.
        horizontalCalendar.calendarListener = object : HorizontalCalendarListener() {
            override fun onDateSelected(date: Calendar, position: Int) {
                // on below line we are printing date
                // in the logcat which is selected.
                Log.e("TAG", "CURRENT DATE IS $date")
            }
        }

        view.findViewById<Button>(R.id.navigation_main).setOnClickListener{
            it.findNavController().navigate(R.id.action_teamMainpage_to_mainMainpage)
        }
        view.findViewById<Button>(R.id.navigation_team).setOnClickListener{

        }
        view.findViewById<Button>(R.id.navigation_gain).setOnClickListener{

        }
        view.findViewById<Button>(R.id.navigation_setting).setOnClickListener{

        }
        return view


    }

}