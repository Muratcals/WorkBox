package com.muratcalis.workbox.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.muratcalis.workbox.R
import com.muratcalis.workbox.adapter.KategoriDetayAdapter
import com.muratcalis.workbox.model.Filmler
import com.muratcalis.workbox.viewModel.KategoriDetayViewModel
import kotlinx.android.synthetic.main.fragment_kategori.*
import kotlinx.android.synthetic.main.fragment_kategori_detay.*

class KategoriDetayFragment : Fragment() {
    val viewModel =KategoriDetayViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kategori_detay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.let {
            if(it!=null){
                val turAdi =KategoriDetayFragmentArgs.fromBundle(it).turAdi
                viewModel.getFilm(view,turAdi)
                viewModel.getFilmler.observe(viewLifecycleOwner, Observer {
                    val adapter =KategoriDetayAdapter(it as ArrayList<Filmler>)
                    detayRecylerr.adapter=adapter
                    detayRecylerr.layoutManager=GridLayoutManager(view.context,2)
                })
            }
        }
    }
}