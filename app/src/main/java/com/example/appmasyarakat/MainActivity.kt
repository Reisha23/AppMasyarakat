package com.example.appmasyarakat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private lateinit var btnLogin: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLogin = findViewById(R.id.btnLogin)
        
        btnLogin.setOnClickListener{
            val mvHome = Intent(this, Home::class.java)
            startActivity(mvHome)
    }

        }
    }
}
