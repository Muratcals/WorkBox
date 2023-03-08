package com.muratcalis.workbox.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.muratcalis.workbox.R
import com.muratcalis.workbox.adapter.FilmlerAdapter
import com.muratcalis.workbox.model.Filmler
import com.muratcalis.workbox.viewModel.FilmViewModel
import kotlinx.android.synthetic.main.fragment_film.*

class FilmFragment : Fragment() {
    private val viewModel = FilmViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFilm(view)
        viewModel.filmler.observe(viewLifecycleOwner, Observer {
            val adapter =FilmlerAdapter(it as ArrayList<Filmler>)
            recyclerViewFilm.adapter=adapter
            recyclerViewFilm.layoutManager=GridLayoutManager(view.context,3)
        })
    }
}