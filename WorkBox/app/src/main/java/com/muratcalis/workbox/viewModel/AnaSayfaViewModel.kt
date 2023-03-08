package com.muratcalis.workbox.viewModel

import android.os.Build
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.muratcalis.workbox.model.Filmler
import java.time.LocalDateTime

class AnaSayfaViewModel:ViewModel() {
    var filmlerMutable = MutableLiveData<List<Filmler>>()
    val kapakFilm =MutableLiveData<List<Filmler>>()
    private lateinit var db :FirebaseFirestore

    fun yeniCikanlar(view: View){
        db = FirebaseFirestore.getInstance()
        val deneme =ArrayList<Filmler>()
        val anlik =LocalDateTime.now().year
        db.collection("Filmler").whereEqualTo("Film Onay",true).addSnapshotListener { value, error ->
            if (error!=null){
                Toast.makeText(view.context,error.localizedMessage,Toast.LENGTH_SHORT).show()
            }else{
                if (value!=null){
                    deneme.clear()
                    val filmlerr =value.documents
                    for (film in filmlerr){
                        val cikis =film.get("Çıkış Tarihi") as String
                        val yil =cikis.substring(cikis.length-4)
                        if (yil.toInt()==anlik){
                                val filmId =film.id
                                val filmAdi =film.get("Film Adi") as String
                                val filmYonetmen =film.get("Film Yönetmen") as String
                                val imdb =film.get("Imdb") as String
                                val sure =film.get("Süre") as String
                                val cikisTarihi =film.get("Çıkış Tarihi") as String
                                val filmKonu=film.get("Film Konu") as String
                                val filmTur=film.get("Film Türü") as String
                                val posterUrl =film.get("Poster Url") as String
                                deneme.add(Filmler(filmId,filmAdi,filmYonetmen,imdb,sure,cikisTarihi,filmKonu,filmTur,posterUrl))
                        }
                        filmlerMutable.value=deneme
                    }
                }
            }
        }
    }
    fun kapak(view: View){
        db = FirebaseFirestore.getInstance()
        val deneme =ArrayList<Filmler>()
        val anlik =LocalDateTime.now().year
        db.collection("Filmler").whereEqualTo("Film Onay",true).addSnapshotListener { value, error ->
            if (error!=null){
                Toast.makeText(view.context,error.localizedMessage,Toast.LENGTH_SHORT).show()
            }else{
                if (value!=null){
                    deneme.clear()
                    var sayac = 0
                    val filmlerr =value.documents
                    for (film in filmlerr){
                        val cikis =film.get("Çıkış Tarihi") as String
                        val yil =cikis.substring(cikis.length-4)
                        if (yil.toInt()==anlik){
                            sayac += 1
                            val filmId =film.id
                            val filmAdi =film.get("Film Adi") as String
                            val filmYonetmen =film.get("Film Yönetmen") as String
                            val imdb =film.get("Imdb") as String
                            val sure =film.get("Süre") as String
                            val cikisTarihi =film.get("Çıkış Tarihi") as String
                            val filmKonu=film.get("Film Konu") as String
                            val filmTur=film.get("Film Türü") as String
                            val posterUrl =film.get("Poster Url") as String
                            deneme.add(Filmler(filmId,filmAdi,filmYonetmen,imdb,sure,cikisTarihi,filmKonu,filmTur,posterUrl))
                        }
                        if (sayac==10){
                            kapakFilm.value=deneme
                            break
                        }
                    }
                }
            }
        }
    }
    fun cokIzlenen(view: View){
        db = FirebaseFirestore.getInstance()
        val deneme =ArrayList<Filmler>()
        db.collection("Filmler").whereEqualTo("Film Onay",true).addSnapshotListener { value, error ->
            if (error!=null){
                Toast.makeText(view.context,error.localizedMessage,Toast.LENGTH_SHORT).show()
            }else{
                if (value!=null){
                    val filmlerr =value.documents
                    deneme.clear()
                    for (film in filmlerr){
                        val Imdb =film.get("Imdb") as String
                            if(Imdb.toDouble()>=4.0 ){
                                val filmId =film.id
                                val filmAdi =film.get("Film Adi") as String
                                val filmYonetmen =film.get("Film Yönetmen") as String
                                val imdb =film.get("Imdb") as String
                                val sure =film.get("Süre") as String
                                val cikisTarihi =film.get("Çıkış Tarihi") as String
                                val filmKonu=film.get("Film Konu") as String
                                val filmTur=film.get("Film Türü") as String
                                val posterUrl =film.get("Poster Url") as String
                                deneme.add(Filmler(filmId,filmAdi,filmYonetmen,imdb,sure,cikisTarihi,filmKonu,filmTur,posterUrl))
                            }
                        filmlerMutable.value=deneme
                    }
                }
            }
        }
    }
}