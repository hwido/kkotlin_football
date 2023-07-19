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

class signin : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_page)


        auth = Firebase.auth

        // 회원가입 시키기
        val signinBtn = findViewById<Button>(R.id.signinBtn)
        signinBtn?.setOnClickListener {
            val ID = findViewById<EditText>(R.id.IDSigninArea)
            val password = findViewById<EditText>(R.id.passwordSigninArea)
            val email = findViewById<EditText>(R.id.emailSigninArea)
            val phoneNumber = findViewById<EditText>(R.id.phoneNumberSigninArea)

            // 현재 회원가입시 ID, password, email, phoneNumbwer 입력하게 해둔 상태
            // 이를 데이터베이스에 저장하는 기능 필요(우리가 배운 것은 아이디,비밀번호만 저장됨)
            // 개복치 강의 중에 데이터 베이스 따로 저장하는 방식이 하나 있었는데 그것을 써야하나? 혹은 파이어베이스 말고 다른 방식 채택 위해 하나 더 연결해야할지도
            // 인터넷 찾아보면 sql과 php 연동하여 저장하는 방식 채택하기에, 이에 대한 회의 필요


            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show()
                        
                        // 회원가입 성공시, MainActivity인 로그인 페이지로 넘어가게 된다
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}