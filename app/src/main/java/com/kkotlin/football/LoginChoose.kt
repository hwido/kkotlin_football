package com.hwido.football

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hwido.football.databinding.LoginChooseBinding

class LoginChoose : AppCompatActivity() {
    private lateinit var binding : LoginChooseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginChooseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.teamCardView.setOnClickListener { // 원래 teamCardView가 아닌, login_choose_teamCardView 사용하려 했으나, 그럴 경우 에러 발생
            goToTeamMaker()
        }
        binding.individualCardView.setOnClickListener { // 원래 individualCardView가 아닌, login_choose_individualCardView 사용하려 했으나, 그럴 경우 에러 발생
            goToIndividualMaker()
        }
    }

    private fun goToTeamMaker(){
        val intent = Intent(baseContext, LoginTeamActivity::class.java)
        startActivity(intent)
    }
    private fun goToIndividualMaker(){
        val intent = Intent(baseContext, LoginIndividualActivity::class.java)
        startActivity(intent)
    }
}