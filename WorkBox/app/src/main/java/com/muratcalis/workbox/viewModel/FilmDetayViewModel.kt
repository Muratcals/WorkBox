package com.muratcalis.workbox.viewModel

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.muratcalis.workbox.model.Filmler
import com.muratcalis.workbox.model.Oyuncular

class FilmDetayViewModel :ViewModel() {
    private lateinit var db :FirebaseFirestore
    val filmler =MutableLiveData<List<Filmler>>()
    val oyuncular=MutableLiveData<List<Oyuncular>>()
    var filmFragman1 =MutableLiveData<List<String>>()
    var filmFragman2 =MutableLiveData<List<String>>()
    fun getData(view: View,filmId:String){
        db = FirebaseFirestore.getInstance()
        val deneme =ArrayList<Filmler>()
        db.collection("Filmler").whereEqualTo("Film Onay",true).addSnapshotListener { value, error ->
            if (error!=null){
                Toast.makeText(view.context,error.localizedMessage,Toast.LENGTH_SHORT).show()
            }else{
                if (value!=null){
                    val veriler =value.documents
                    deneme.clear()
                    for (film in veriler){
                        if (filmId.equals(film.id)){
                            val filmIdd=film.id
                            val filmAdi =film.get("Film Adi") as String
                            val filmYonetmen =film.get("Film Yönetmen") as String
                            val imdb =film.get("Imdb") as String
                            val sure =film.get("Süre") as String
                            val cikisTarihi =film.get("Çıkış Tarihi") as String
                            val filmKonu=film.get("Film Konu") as String
                            val filmTur=film.get("Film Türü") as String
                            val posterUrl =film.get("Poster Url") as String
                            deneme.add(Filmler(filmIdd,filmAdi,filmYonetmen,imdb,sure,cikisTarihi,filmKonu,filmTur,posterUrl))
                        }
                    }
                    filmler.value=deneme
                }
            }
        }
    }
    fun getOyuncu(view:View,FilmId:String){
        db=FirebaseFirestore.getInstance()
        val yedek =ArrayList<Oyuncular>()
        db.collection("Oyuncular").addSnapshotListener { value, error ->
            if (error!=null){
                Toast.makeText(view.context,error.localizedMessage,Toast.LENGTH_SHORT).show()
            }else{
                if (value!==null){
                    val degerler =value.documents
                    for (deger in degerler){
                        if (FilmId.equals(deger.get("Film Id") as String)){
                            val oyuncuAdi =deger.get("Oyuncu Adı") as String
                            val oyuncuRolu =deger.get("Oyuncu Rolu") as String
                            val oyuncuPoster =deger.get("Oyuncu Poster") as String
                            yedek.add(Oyuncular(oyuncuAdi,oyuncuRolu,oyuncuPoster))
                        }
                    }
                    oyuncular.value=yedek
                }
            }
        }
    }
    fun getFragman(view: View,FilmAd:String){
        db=FirebaseFirestore.getInstance()
        db.collection("Filmler").whereEqualTo("Film Adi",FilmAd).addSnapshotListener { value, error ->
            if (error!=null){
                Toast.makeText(view.context,error.localizedMessage,Toast.LENGTH_SHORT).show()
            }else{
                if (value!==null){
                    val degerler =value.documents
                    for (deger in degerler){
                        val fragman1 =deger.get("Film Fragman 1") as String
                        filmFragman1.value= listOf(fragman1)
                        val fragman2 =deger.get("Film Fragman 2") as String
                        filmFragman2.value= listOf(fragman2)
                    }
                }
            }
        }
    }
}