package com.kkotlin.football

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.hwido.football.R


class MainMainpage : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_mainpage)

        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.add(R.id.main_mainpage_fragment, MainMainpageFragmentSchedule())
        fragmentTransaction.commit()

        // 하단 버튼
        val mainBtnteam = findViewById<Button>(R.id.main_button_team)
        val mainBtnIndividual = findViewById<Button>(R.id.main_button_individual)
        val mainBtnSetting = findViewById<Button>(R.id.main_button_setting)


        mainBtnteam.setOnClickListener {
            val intent = Intent(baseContext, MainTeampage::class.java)
            startActivity(intent)
        }

        mainBtnIndividual.setOnClickListener {
        }

        mainBtnSetting.setOnClickListener {
            val intent = Intent(baseContext, MainPersonalInformationpage::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(v: View?) {
        Log.d("버튼 클릭!", "버튼 클릭!")

        var fr: Fragment? = null

        when (v?.id) {
            R.id.main_mainpage_schedule -> {
                fr = MainMainpageFragmentSchedule()
            }
            R.id.main_mainpage_teamranking -> {
                fr = MainMainpageFragmentTeamranking()
            }
            R.id.main_mainpage_teamrecord -> {
                fr = MainMainpageFragmentTeamrecord()
            }
            R.id.main_mainpage_individualrecord -> {
                fr = MainMainpageFragmentIndividualrecord()
            }
        }

        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()

        // fragment 교체
        fragmentTransaction.replace(R.id.main_mainpage_fragment, fr!!)
        fragmentTransaction.commit()
    }


}