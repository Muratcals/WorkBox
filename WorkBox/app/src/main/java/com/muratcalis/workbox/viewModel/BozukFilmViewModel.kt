package com.muratcalis.workbox.viewModel

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class BozukFilmViewModel : ViewModel() {
    private lateinit var db:FirebaseFirestore
    val filmlerr =MutableLiveData<List<String>>()
    fun getFilmAdi(view: View){
        db = FirebaseFirestore.getInstance()
        val yedek =ArrayList<String>()
        db.collection("Filmler").addSnapshotListener { value, error ->
            if (error!=null){
                Toast.makeText(view.context,error.localizedMessage, Toast.LENGTH_SHORT).show()
            }else{
                if (value!=null){
                    yedek.clear()
                    val degerler =value.documents
                    for (deger in degerler){
                        val filmAdi =deger.get("Film Adi") as String
                        yedek.add(filmAdi)
                    }
                    filmlerr.value=yedek
                }
            }
        }
    }
}