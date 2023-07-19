package com.example.football

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        auth = Firebase.auth

        // 로그인 시키기
        val loginBtn = findViewById<Button>(R.id.loginBtn)
        loginBtn.setOnClickListener {
            val email = findViewById<EditText>(R.id.emailArea)
            val password = findViewById<EditText>(R.id.passwordArea)

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
        }

        // 회원가입 버튼 누르면 signin인 회원가입 페이지로 넘어가게 된다
        val joininBtn = findViewById<Button>(R.id.joinBtn)
        joininBtn.setOnClickListener {
            val intent = Intent(this, signin::class.java)
            startActivity(intent)
        }
    }
}