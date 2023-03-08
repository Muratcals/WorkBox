package com.muratcalis.workbox.viewModel

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.muratcalis.workbox.model.Filmler

class kategoriViewModel : ViewModel() {
    val filmler = MutableLiveData<List<Filmler>>()
    val getFilmler =MutableLiveData<List<Filmler>>()
    val allTur =MutableLiveData<List<String>>()
    private lateinit var db :FirebaseFirestore
    fun allGetData(view:View, tur :String){
        db= FirebaseFirestore.getInstance()
        db.collection("Filmler").whereEqualTo("Film Onay",true).addSnapshotListener { value, error ->
            if (error!=null){
                Toast.makeText(view.context,error.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                if (value!=null){
                    val degerler = value.documents
                    for (deger in degerler){
                        val filmTur =deger.get("Film Türü") as String
                        if (filmTur.contains(tur,true)){
                            val filmAdi=deger.get("Film Adi") as String
                            println(filmAdi)
                        }
                    }
                }
            }
        }
    }
    fun allTur(view: View){
        db= FirebaseFirestore.getInstance()
        val turler =ArrayList<String>()
        db.collection("Türler").addSnapshotListener { value, error ->
            if (error!=null){
                Toast.makeText(view.context,error.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                if (value!=null){
                    val degerler = value.documents
                    for (deger in degerler){
                        val turAdi=deger.get("Tür Ad") as String
                        turler.add(turAdi)
                    }
                    allTur.value=turler
                }
            }
        }
    }
}