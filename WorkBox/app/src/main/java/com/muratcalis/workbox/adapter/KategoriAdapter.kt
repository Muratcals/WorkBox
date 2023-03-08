package com.muratcalis.workbox.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muratcalis.workbox.R
import com.muratcalis.workbox.view.KategoriFragmentDirections
import kotlinx.android.synthetic.main.kategorigorunum.view.*

class KategoriAdapter(val tur:ArrayList<String>):RecyclerView.Adapter<KategoriAdapter.KategoriVh>() {
    class KategoriVh(view: View):RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriVh {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.kategorigorunum,parent,false)
        return KategoriVh(view)
    }

    override fun onBindViewHolder(holder: KategoriVh, position: Int) {
        holder.itemView.tur.text=tur[position]
        holder.itemView.tur.setOnClickListener {
            val action =KategoriFragmentDirections.actionKategoriFragmentToKategoriDetayFragment(tur[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return tur.size
    }
}