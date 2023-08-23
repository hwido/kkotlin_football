package com.kkotlin.football

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hwido.football.R

class LoginMainpage : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_mainpage)

        auth = Firebase.auth

        // 로그인 시키기
        val loginBtn = findViewById<Button>(R.id.login_mainpage_loginBtn)
        loginBtn.setOnClickListener {
            val email = findViewById<EditText>(R.id.login_mainpage_emailArea)
            val password = findViewById<EditText>(R.id.login_mainpage_passwordArea)

            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show()
                        
                        // 하단에 로그인 성공시 다음 페이지로 넘어가는 코드 필요
                    }
                    else {
                        Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
                    }
                }

            // 메인 페이지로 넘어간다
            val intent = Intent(this, MainFragmentLayout::class.java)
            startActivity(intent)
        }

        // 회원가입 버튼 누르면 signin인 회원가입 페이지로 넘어가게 된다
        val joininBtn = findViewById<Button>(R.id.login_mainpage_joinBtn)
        joininBtn.setOnClickListener {
            val intent = Intent(this, LoginSigninpage::class.java)
            startActivity(intent)
        }
    }
}