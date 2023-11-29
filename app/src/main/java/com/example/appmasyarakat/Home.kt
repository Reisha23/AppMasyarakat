package com.example.appmasyarakat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Home : AppCompatActivity() {
    private lateinit var btnHome: ImageView
    private lateinit var btnInsert : ImageView
    private lateinit var btnHistory: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnHome = findViewById(R.id.btnhome)
        btnInsert = findViewById(R.id.btnInsert)
        btnHistory = findViewById(R.id.btnHistory)

        btnHome.setOnClickListener {
            val mvHome = Intent(this, Home::class.java)
            startActivity(mvHome)
        }

        btnInsert.setOnClickListener {
            val mvInsert = Intent(this,UploadActivity::class.java)
            startActivity(mvInsert)
        }

        btnHistory.setOnClickListener {
            val mvHistory = Intent(this,History::class.java)
            startActivity(mvHistory)
        }
    }
}