package com.example.appmasyarakat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BeritaViewModel : ViewModel() {
    var _listberita: MutableLiveData<MutableList<BeritaModel>> = MutableLiveData(listBerita)

    val lisBerita: LiveData<MutableList<BeritaModel>>
        get() = _listberita

    val _listBeritaHistory: MutableLiveData<MutableList<BeritaModel>> = MutableLiveData()

   val listBeritaHistory: LiveData<MutableList<BeritaModel>>
       get() = _listBeritaHistory
}
