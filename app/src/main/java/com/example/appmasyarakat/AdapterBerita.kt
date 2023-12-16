package com.example.appmasyarakat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdapterBerita(val list: MutableList<BeritaModel>,val konteks: Context): RecyclerView.Adapter<AdapterBerita.BeritaViewHolder>() {

    class BeritaViewHolder(baris: View): RecyclerView.ViewHolder(baris){
        val nama = baris.findViewById<TextView>(R.id.HomeNama)
        val alamat = baris.findViewById<TextView>(R.id.HomeAlamat)
        val foto = baris.findViewById<ImageView>(R.id.HomeFoto)
        val informasi = baris.findViewById<TextView>(R.id.HomeInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.home_news,parent,false)
        return BeritaViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val bind = list[position]
        holder.alamat.text = bind.alamat
        Glide.with(konteks).load(bind.foto).placeholder(R.drawable.home_vector).into(holder.foto)
        holder.nama.text = bind.nama
        holder.informasi.text = bind.informasi
    }
}