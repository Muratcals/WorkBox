package com.muratcalis.workbox.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.recyclerview.widget.RecyclerView
import com.muratcalis.workbox.R
import com.muratcalis.workbox.model.Filmler
import com.muratcalis.workbox.util.downloadImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.anasayfa_gorunum.view.*
import kotlinx.android.synthetic.main.kapak_gorunum.view.*
import kotlinx.android.synthetic.main.oyuncu_gorunum.view.*

class KapakRecyler(val resimler:ArrayList<Filmler>):RecyclerView.Adapter<KapakRecyler.KapakVh>() {
    class KapakVh(itemView : View) :RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KapakVh {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.kapak_gorunum,parent,false)
        return KapakVh(view)
    }

    override fun onBindViewHolder(holder: KapakVh, position: Int) {
        holder.itemView.scroll.text="${position+1}/${resimler.size}"
        holder.itemView.kapakImage.downloadImage(resimler[position].filmUrl,holder.itemView.context)
        holder.itemView.kapakFilmAdi.text=resimler[position].filmAdi
    }

    override fun getItemCount(): Int {
        return resimler.size
    }
}