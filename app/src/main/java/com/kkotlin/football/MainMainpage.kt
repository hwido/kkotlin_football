package com.kkotlin.football

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.hwido.football.R
import devs.mulham.horizontalcalendar.HorizontalCalendar
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener
import java.util.Calendar

class MainMainpage : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        Log.d("frg oncreate","frg oncreate")








    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("oncreatedveiw","oncreatedview")

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.main_mainpage, container, false)

        // on below line we are setting up our horizontal calendar view and passing id our calendar view to it.


        view.findViewById<Button>(R.id.navigation_main).setOnClickListener{

        }
        view.findViewById<Button>(R.id.navigation_team).setOnClickListener{
            it.findNavController().navigate(R.id.action_mainMainpage_to_mainTeampage)
        }
        view.findViewById<Button>(R.id.navigation_gain).setOnClickListener{

        }
        view.findViewById<Button>(R.id.navigation_setting).setOnClickListener{

        }
        return view


    }

}