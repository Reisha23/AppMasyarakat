package com.example.appmasyarakat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appmasyarakat.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.Scanner

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnRegister.setOnClickListener{
            val email = binding.rgstEmail.text.toString()
            val pass = binding.rgstPass.text.toString()
            val rePass = binding.rgstRePass.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && rePass.isNotEmpty()) {
                if (pass != rePass){
                    Toast.makeText(this, "Masukkan Password Yang Sama", Toast.LENGTH_SHORT).show()
                }else{
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}
