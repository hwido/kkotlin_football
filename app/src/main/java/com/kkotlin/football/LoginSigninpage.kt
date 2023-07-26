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

class LoginSigninpage : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_signinpage)


        auth = Firebase.auth

        // 회원가입 시키기
        val signinBtn = findViewById<Button>(R.id.signinBtn)
        signinBtn?.setOnClickListener {
            val password = findViewById<EditText>(R.id.login_signinpage_passwordSigninArea)
            val email = findViewById<EditText>(R.id.login_signinpage_emailSigninArea)
            val phoneNumber = findViewById<EditText>(R.id.login_signinpage_phoneNumberSigninArea)

            // 현재 회원가입시 password, email, phoneNumbwer 입력하게 해둔 상태
            // 이를 데이터베이스에 저장하는 기능 필요(우리가 배운 것은 아이디,비밀번호만 저장됨)
            // 개복치 강의 중에 데이터 베이스 따로 저장하는 방식이 하나 있었는데 그것을 써야하나? 혹은 파이어베이스 말고 다른 방식 채택 위해 하나 더 연결해야할지도


            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show()
                        
                        // 회원가입 성공시, 팀/개인 가입 페이지로 넘어가게 된다
                        val intent = Intent(this, LoginChoose::class.java)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}