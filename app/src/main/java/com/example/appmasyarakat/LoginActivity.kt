package com.example.appmasyarakat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.Scanner

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
    }

    fun main() {
        val scanner = Scanner(System.`in`)
        val username = "user123"
        val password = "pass456"

        print("Masukkan username: ")
        val inputUsername = scanner.nextLine()

        print("Masukkan password: ")
        val inputPassword = scanner.nextLine()

        if (inputUsername == username && inputPassword == password) {
            println("Login berhasil! Selamat datang, $username.")
        } else {
            println("Login gagal. Username atau password salah.")
        }
    }
}