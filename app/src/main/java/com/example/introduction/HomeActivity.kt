package com.example.introduction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val ivProfile = findViewById<ImageView>(R.id.iv_profile)
        val profileId = findViewById<EditText>(R.id.et_profile_id)
        val btnFinish = findViewById<Button>(R.id.btn_finish)
        val idData = intent.getStringExtra("LoginId")

        if (intent.hasExtra("LoginId")) {
            profileId.setText(idData)
        }

        // HomeActivity가 열릴때 마다 프로필 이미지가 profile1, profile2, profile3, profile4, profile5 중에 하나로 랜덤하게 설정됨
        val profileList = listOf(
            R.drawable.profile1,
            R.drawable.profile2,
            R.drawable.profile3,
            R.drawable.profile4,
            R.drawable.profile5
        )
        ivProfile.setImageResource(profileList.random())

        btnFinish.setOnClickListener {
            finish()
        }
    }
}