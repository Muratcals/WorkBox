package com.muratcalis.workbox.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.firebase.firestore.FirebaseFirestore
import com.muratcalis.workbox.R
import com.muratcalis.workbox.viewModel.BozukFilmViewModel
import kotlinx.android.synthetic.main.fragment_bozuk_film.*

class BozukFilmFragment : Fragment() {

    private val viewModel = BozukFilmViewModel()
    private var sorun :String?=null
    private var filmAdi:String?=null
    private lateinit var db:FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bozuk_film, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        @Suppress("DEPRECATION")
        super.onActivityCreated(savedInstanceState)
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress_bozukFilm.visibility=View.GONE
        db= FirebaseFirestore.getInstance()
        viewModel.getFilmAdi(view)
        viewModel.filmlerr.observe(viewLifecycleOwner, Observer {
            val filmler = ArrayAdapter(view.context, R.layout.spinner,it)
            filmlerSpinner.adapter=filmler
            val sorunlarr = arrayOf(
                "Film adı yanlış veya eksik yazılmış",
                "Fragman 1 linki kırık",
                "Fragman 2 linki kırık",
                "Çıkış yılı yanlış yazılmış",
                "Film posteri gözükmüyor",
                "Diger"
            )
            val sorunlar =ArrayAdapter(view.context,R.layout.spinner,sorunlarr)
            sorunlarSpinner.adapter=sorunlar
            sorunlarSpinner.setSelection(0)
        })
        gonder.setOnClickListener {
            progress_bozukFilm.visibility=View.VISIBLE
            val hashMap= hashMapOf<String,Any>()
            filmAdi=filmlerSpinner.selectedItem.toString()
            sorun=sorunlarSpinner.selectedItem.toString()
            hashMap.put("Film Adi",filmAdi!!)
            hashMap.put("Film Sorunu",sorun!!)
            db.collection("Bozuk Film").add(hashMap).addOnSuccessListener {
                Toast.makeText(view.context,"Bildirim başarıyla oluşturulmuştur",Toast.LENGTH_SHORT).show()
                sorunlarSpinner.setSelection(0)
                filmlerSpinner.setSelection(0)
                progress_bozukFilm.visibility=View.GONE
            }.addOnSuccessListener {

            }
        }
    }
}