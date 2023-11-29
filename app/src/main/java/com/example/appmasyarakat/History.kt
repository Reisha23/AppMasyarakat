package com.example.appmasyarakat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class History : AppCompatActivity() {
    private lateinit var btnBack: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            this.finish()
        }
    }
}