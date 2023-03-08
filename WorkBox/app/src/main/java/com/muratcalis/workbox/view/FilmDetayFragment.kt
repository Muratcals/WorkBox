package com.muratcalis.workbox.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.muratcalis.workbox.R
import com.muratcalis.workbox.adapter.OyuncuAdapter
import com.muratcalis.workbox.model.Oyuncular
import com.muratcalis.workbox.viewModel.FilmDetayViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_film_detay.*


class FilmDetayFragment : Fragment() {
    private var fragman1 :String?=null
    private var fragman2 :String?=null
    val viewModel =FilmDetayViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film_detay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.let {
            val filmAd =FilmDetayFragmentArgs.fromBundle(it!!).filmAd
            val filmidd = FilmDetayFragmentArgs.fromBundle(it).filmId
            viewModel.getData(view,filmidd)
            viewModel.filmler.observe(viewLifecycleOwner, Observer {
                detayFilmAdi.text="Film Adı : ${it[0].filmAdi}"
                detayFilmImdb.text="Film Puanı : ${it[0].imdb}"
                detayFilmYonetmen.text="Film Yönetmen : ${it[0].filmYönetmen}"
                detayFilmSure.text="Film Süresi : ${it[0].sure}"
                detayFilmtarih.text="Çıkış Tarihi : ${it[0].cikisTarihi}"
                detayFilmKonusu.text=it[0].filmKonu
                Picasso.get().load(it[0].filmUrl).into(detayImage)
                viewModel.getOyuncu(view,filmidd)
                viewModel.oyuncular.observe(viewLifecycleOwner, Observer {
                    val adapter =OyuncuAdapter(it as ArrayList<Oyuncular>)
                    detayRecyler.adapter=adapter
                    detayRecyler.layoutManager=LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
                })
            })
            viewModel.getFragman(view,filmAd)
            viewModel.filmFragman1.observe(viewLifecycleOwner, Observer { fragman->
                fragman1=fragman[0]
            })
            viewModel.filmFragman2.observe(viewLifecycleOwner, Observer { fragman->
                fragman2=fragman[0]
                if(fragman[0]==""){
                    Fragman2.visibility=View.INVISIBLE
                }

            })
            Fragman1.setOnClickListener {
                val action =Intent(Intent.ACTION_VIEW)
                action.data= Uri.parse(fragman1)
                startActivity(action)
            }
            Fragman2.setOnClickListener {
                val action =Intent(Intent.ACTION_VIEW)
                action.data= Uri.parse(fragman2)
                startActivity(action)
            }
        }
    }
}