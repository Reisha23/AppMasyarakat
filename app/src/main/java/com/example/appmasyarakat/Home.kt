package com.example.appmasyarakat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class Home : AppCompatActivity() {
    private lateinit var btnHome: ImageView
    private lateinit var btnInsert : ImageView
    private lateinit var btnHistory: ImageView
    private lateinit var recylerBerita: RecyclerView
    private lateinit var viewModel: BeritaViewModel
    private lateinit var firestore: FirebaseFirestore
    private lateinit var progress: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        firestore = FirebaseFirestore.getInstance()
        progress = findViewById(R.id.progressBar)
        viewModel = ViewModelProvider(this).get(BeritaViewModel::class.java)


        btnHome = findViewById(R.id.btnhome)
        btnInsert = findViewById(R.id.btnInsert)
        btnHistory = findViewById(R.id.btnHistory)
        recylerBerita = findViewById(R.id.recylerBerita)
        GlobalScope.launch { getDataFromFirestore() }

        viewModel.lisBerita.observe(this){newValue ->
            recylerBerita.layoutManager = LinearLayoutManager(this)
            recylerBerita.adapter = AdapterBerita(newValue,this)
            if (newValue.size != 0){
                progress.visibility = View.GONE
            }
        }



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

    suspend fun getDataFromFirestore(){
        val data = firestore.collection("berita").get().await()
        withContext(Dispatchers.IO){
            data?.let {document ->
                val listBeritaDoc = document.map {doc ->
                    BeritaModel(
                        doc.getString("nama")?: "",
                        doc.getString("alamat")?: "",
                        doc.getString("foto")?:"",
                        doc.getString("informasi")?:"",
                        doc.getString("email")?: ""
                    )



                }
                viewModel._listberita.postValue(listBeritaDoc.toMutableList())

            }
        }
    }



}