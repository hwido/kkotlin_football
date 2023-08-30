package com.kkotlin.football

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.hwido.football.R


class MainPersonalInformationpage : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_personalinformaionpage)

        Log.d("activity onCreate", "activity onCreate")



        //하단버튼
        val mainBtnmain = findViewById<Button>(R.id.main_button_main)
        val mainBtnteam = findViewById<Button>(R.id.main_button_team)
        val mainBtnIndividual = findViewById<Button>(R.id.main_button_individual)
        val mainBtnSetting = findViewById<Button>(R.id.main_button_setting)

        mainBtnmain.setOnClickListener {
            val intent = Intent(baseContext, MainMainpage::class.java)
            startActivity(intent)
        }

        mainBtnteam.setOnClickListener {
            val intent = Intent(baseContext, MainTeampage::class.java)
            startActivity(intent)
        }

        mainBtnIndividual.setOnClickListener {
            //
        }
    }
}