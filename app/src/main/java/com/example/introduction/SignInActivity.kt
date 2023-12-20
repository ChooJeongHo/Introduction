package com.example.introduction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class SignInActivity : AppCompatActivity() {
    private lateinit var requestLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val id = findViewById<EditText>(R.id.etSignInId)
        val pw = findViewById<EditText>(R.id.etSignInPw)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val btnSignUp = findViewById<Button>(R.id.btnSignUp)

        // SignUp에서 회원가입한 id, pw를 받아와서 자동으로 입력되도록 함
        requestLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val data: Intent? = it.data
                id.setText(data?.getStringExtra("SignUpId"))
                pw.setText(data?.getStringExtra("SignUpPw"))
            }
        }

        btnSignIn.setOnClickListener {
            // id, pw 중 하나라도 입력되지 않았을 때, 로그인 버튼이 눌려져도 로그인이 되지 않음
            if (id.text.toString().isEmpty() || pw.text.toString().isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_msg_signInError), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, getString(R.string.toast_msg_signInSuccess), Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("LoginId", id.text.toString())
                startActivity(intent)
            }
        }

        // requestLauncher를 여기서 호출해야함
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            requestLauncher.launch(intent)
        }
    }
}