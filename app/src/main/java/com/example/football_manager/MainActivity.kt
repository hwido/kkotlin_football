package com.example.football_manager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.football_manager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.teamCardView.setOnClickListener {
            goToTeamMaker()
        }
        binding.individualCardView.setOnClickListener {
            goToIndividualMaker()
        }

        }
    private fun goToTeamMaker(){
        val intent = Intent(baseContext, TeamViewActivity::class.java)
        startActivity(intent)
    }
    private fun goToIndividualMaker(){
        val intent = Intent(baseContext, IndividualActivity::class.java)
        startActivity(intent)
    }

}