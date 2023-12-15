package com.example.appmasyarakat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class History : AppCompatActivity() {
    private lateinit var btnBack: ImageView
    private lateinit var recylerHistory: RecyclerView
    private lateinit var firestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var viewModelHistory: BeritaViewModel
    private lateinit var progresBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        recylerHistory = findViewById(R.id.recylerBeritaHistory)
        progresBar = findViewById(R.id.progressBar2)
        viewModelHistory = ViewModelProvider(this)[BeritaViewModel::class.java]
        firestore = FirebaseFirestore.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()

        GlobalScope.launch { getDataFilterFireStore() }

        viewModelHistory.listBeritaHistory.observe(this){newValue ->
            recylerHistory.layoutManager = LinearLayoutManager(this)
            recylerHistory.adapter = AdapterBerita(newValue,this)
            if (newValue.size != 0){
                progresBar.visibility = View.GONE
            }
        }

        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            this.finish()
        }
    }

    suspend fun getDataFilterFireStore(){
        val data = firestore.collection("berita").whereEqualTo("email",firebaseAuth.currentUser!!.email.toString()).get().await()
        withContext(Dispatchers.IO){
            data?.let {document ->
                val listBeritaHistory = document.map {doc ->
                    BeritaModel(
                        doc.getString("nama")?: "",
                        doc.getString("alamat")?: "",
                        doc.getString("foto")?:"",
                        doc.getString("informasi")?:"",
                        doc.getString("email")?: ""
                    )

                }
                viewModelHistory._listBeritaHistory.postValue(listBeritaHistory.toMutableList())

            }
        }
    }

}