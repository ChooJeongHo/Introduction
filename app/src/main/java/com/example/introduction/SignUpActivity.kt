package com.example.introduction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val etName = findViewById<EditText>(R.id.et_signUp_name)
        val etId = findViewById<EditText>(R.id.et_signUp_id)
        val etPw = findViewById<EditText>(R.id.et_signUp_pw)
        val btnSign = findViewById<Button>(R.id.btn_sign)
        val nameErrorText = findViewById<TextView>(R.id.name_error_text)
        val idErrorText = findViewById<TextView>(R.id.id_error_text)
        val pwErrorText = findViewById<TextView>(R.id.pw_error_text)

        // name, id, pw가 모두 입력 되었을 때만 회원가입 버튼이 눌려짐
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 아무것도 안함
            }

            override fun afterTextChanged(s: Editable?) {
                if (etName.text.isNotEmpty()) {
                    nameErrorText.visibility = TextView.INVISIBLE
                }
                if (etId.text.isNotEmpty()) {
                    idErrorText.visibility = TextView.INVISIBLE
                }
                if (etPw.text.isNotEmpty()) {
                    pwErrorText.visibility = TextView.INVISIBLE
                }
                btnSign.isEnabled = etName.text.toString().isNotEmpty() && etId.text.toString().isNotEmpty() && etPw.text.toString().isNotEmpty()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 아무것도 안함
            }
        }

        etName.addTextChangedListener(textWatcher)
        etId.addTextChangedListener(textWatcher)
        etPw.addTextChangedListener(textWatcher)

        etName.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && etName.text.isEmpty()) {
                nameErrorText.visibility = TextView.VISIBLE
                nameErrorText.text = "이름을 입력해주세요."
            }
        }

        etId.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && etId.text.isEmpty()) {
                idErrorText.visibility = TextView.VISIBLE
                idErrorText.text = "아이디를 입력해주세요."
            }
        }

        etPw.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && etPw.text.isEmpty()) {
                pwErrorText.visibility = TextView.VISIBLE
                pwErrorText.text = "비밀번호를 입력해주세요."
            }
        }

        btnSign.setOnClickListener {
            // etName, etId, etPw 중 하나라도 입력되지 않았을 때, 회원가입 버튼이 눌려져도 회원가입이 되지 않음
            if (etName.text.isEmpty() || etId.text.isEmpty() || etPw.text.isEmpty()) {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("SignUpId", etId.text.toString())
                intent.putExtra("SignUpPw", etPw.text.toString())
                setResult(RESULT_OK, intent)    // SignUpActivity에서 SignInActivity로 돌아갈 때, SignUpActivity에서 입력한 id, pw를 SignInActivity에 전달
                finish()
            }
        }
    }
}