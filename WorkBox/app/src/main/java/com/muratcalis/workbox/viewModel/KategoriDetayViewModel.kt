package com.muratcalis.workbox.viewModel

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.muratcalis.workbox.model.Filmler

class KategoriDetayViewModel: ViewModel() {
    private lateinit var db:FirebaseFirestore
    val getFilmler = MutableLiveData<List<Filmler>>()
    fun getFilm(view: View, turAdi:String){
        val deneme =ArrayList<Filmler>()
        db=FirebaseFirestore.getInstance()
        db.collection("Filmler").whereEqualTo("Film Onay",true).addSnapshotListener { value, error ->
            if (error!=null){
                Toast.makeText(view.context,error.localizedMessage, Toast.LENGTH_LONG).show()
            }else{
                if (value!=null){
                    val degerler = value.documents
                    for (film in degerler){
                        val filmTur =film.get("Film Türü") as String
                        if (filmTur.contains(turAdi,true)){
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
                    }
                    getFilmler.value=deneme
                }
            }
        }
    }
}