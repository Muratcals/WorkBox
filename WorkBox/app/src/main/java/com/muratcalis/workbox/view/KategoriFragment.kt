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
import com.muratcalis.workbox.adapter.KategoriAdapter
import com.muratcalis.workbox.viewModel.kategoriViewModel
import kotlinx.android.synthetic.main.fragment_kategori.*

class KategoriFragment : Fragment() {
    private val viewModel =kategoriViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kategori, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.allTur(view)
        viewModel.allTur.observe(viewLifecycleOwner, Observer {
            val adapter =KategoriAdapter(it as ArrayList<String>)
            kategoriRecyler.adapter=adapter
            kategoriRecyler.layoutManager=GridLayoutManager(view.context,2)
        })
    }
}