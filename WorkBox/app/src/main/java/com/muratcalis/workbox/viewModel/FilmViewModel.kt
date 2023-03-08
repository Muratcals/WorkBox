package com.muratcalis.workbox.viewModel

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.muratcalis.workbox.model.Filmler

class FilmViewModel :ViewModel() {
    var filmler =MutableLiveData<List<Filmler>>()
    private lateinit var db :FirebaseFirestore

    fun getFilm(view :View){
        db = FirebaseFirestore.getInstance()
        val deneme =ArrayList<Filmler>()
        db.collection("Filmler").whereEqualTo("Film Onay",true).addSnapshotListener { value, error ->
            if (error!=null){
                Toast.makeText(view.context,error.localizedMessage, Toast.LENGTH_SHORT).show()
            }else{
                if (value!=null){
                    val filmlerr =value.documents
                    for (film in filmlerr){
                        val filmId =film.id
                        val filmAdi =film.get("Film Adi") as String
                        val filmYönetmen =film.get("Film Yönetmen") as String
                        val imdb =film.get("Imdb") as String
                        val sure =film.get("Süre") as String
                        val cikisTarihi =film.get("Çıkış Tarihi") as String
                        val filmKonu=film.get("Film Konu") as String
                        val filmTür=film.get("Film Türü") as String
                        val posterUrl =film.get("Poster Url") as String
                        deneme.add(Filmler(filmId,filmAdi,filmYönetmen,imdb,sure,cikisTarihi,filmKonu,filmTür,posterUrl))
                    }
                    filmler.value=deneme
                }
            }
        }
    }
}