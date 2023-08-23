package com.kkotlin.football

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hwido.football.R


class MainFragmentLayout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    val transaction = supportFragmentManager.beginTransaction()
    setContentView(R.layout.main_nav_host)
    transaction.add(R.id.main_nav_host,MainMainpage())
    transaction.commit()
    }
}