package com.kkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.go_to_membership_personal).setOnClickListener {
            val fragment = membershipPersonalFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_display, fragment)
                .commit()
        }
    }
}