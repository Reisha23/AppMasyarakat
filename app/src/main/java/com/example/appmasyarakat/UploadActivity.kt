package com.example.appmasyarakat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UploadActivity : AppCompatActivity() {
    private lateinit var btnUpload: ImageView
    private lateinit var editFoto: EditText
    private lateinit var editNama: EditText
    private lateinit var editAlamat: EditText
    private lateinit var editInformasi: EditText
    private lateinit var firestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        btnUpload = findViewById(R.id.btnUpload)
        editAlamat = findViewById(R.id.IdAlamat)
        editFoto = findViewById(R.id.IdFoto)
        editNama = findViewById(R.id.IdName)
        editInformasi = findViewById(R.id.IdInfo)
        firestore = FirebaseFirestore.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()

        btnUpload.setOnClickListener {
            val nama = editNama.text.toString()
            val alamat = editAlamat.text.toString()
            val foto = editFoto.text.toString()
            val informasi = editInformasi.text.toString()
            if (nama.isEmpty() || alamat.isEmpty() || foto.isEmpty() || informasi.isEmpty()){
                Toast.makeText(this, "Semua Field Harus", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                addDataToFirestore(nama,alamat,foto,informasi)

            }
        }
    }
    fun addDataToFirestore(nama: String,alamat: String,foto:String,informasi:String){
        val addData = hashMapOf(
            "nama" to nama,
            "alamat" to alamat,
            "foto" to foto,
            "informasi" to informasi,
            "email" to firebaseAuth.currentUser!!.email.toString()
        )
        firestore.collection("berita").add(addData).addOnSuccessListener {
            Toast.makeText(this, "Berhasil Menambahkan Data", Toast.LENGTH_SHORT).show()
            this.finish()
        }.addOnFailureListener {
            Toast.makeText(this, "Gagal Menambahkan Data", Toast.LENGTH_SHORT).show()
        }
    }

}
