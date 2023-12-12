package com.example.appmasyarakat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class UploadActivity : AppCompatActivity() {
    private lateinit var btnUpload: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        btnUpload = findViewById(R.id.btnUpload)

        btnUpload.setOnClickListener {
            this.finish()
        }
    }
}
