package com.example.appmasyarakat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.Scanner

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
    fun main() {
        val scanner = Scanner(System.`in`)

        var username: String
        var password: String
        var confirmPassword: String

        print("Masukkan username: ")
        username = scanner.nextLine()

        print("Masukkan password: ")
        password = scanner.nextLine()

        print("Masukkan kembali password: ")
        confirmPassword = scanner.nextLine()

        if (password == confirmPassword) {
            println("Registrasi berhasil! Username: $username, Password: $password")
        } else {
            println("Registrasi gagal. Password tidak cocok.")
        }
    }
}