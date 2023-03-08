package com.muratcalis.workbox.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muratcalis.workbox.R
import com.muratcalis.workbox.model.Oyuncular
import com.muratcalis.workbox.util.downloadImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.oyuncu_gorunum.view.*

class OyuncuAdapter(val oyuncular:ArrayList<Oyuncular>):RecyclerView.Adapter<OyuncuAdapter.OyuncularVh>() {
    class OyuncularVh(itemView: View) :RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OyuncularVh {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.oyuncu_gorunum,parent,false)
        return OyuncularVh(view)
    }

    override fun onBindViewHolder(holder: OyuncularVh, position: Int) {
        holder.itemView.oyuncuIsim.text=oyuncular[position].oyuncuAdi
        holder.itemView.oyuncuIsim2.text=oyuncular[position].oyuncuRol
        holder.itemView.oyuncuImage.downloadImage(oyuncular[position].oyuncuPoster,holder.itemView.context)
    }

    override fun getItemCount(): Int {
        return oyuncular.size
    }
}