package com.muratcalis.workbox.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.muratcalis.workbox.R
import com.muratcalis.workbox.model.Filmler
import com.muratcalis.workbox.util.downloadImage
import com.muratcalis.workbox.view.FilmFragmentDirections
import com.muratcalis.workbox.view.KategoriDetayFragmentDirections
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.anasayfa_gorunum.view.*
import kotlinx.android.synthetic.main.filmgorunum.view.*

class FilmlerAdapter(val filmler :ArrayList<Filmler>) :RecyclerView.Adapter<FilmlerAdapter.FilmlerVH>() {
    class FilmlerVH(itemView : View) :RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmlerVH {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.filmgorunum,parent,false)
        return FilmlerVH(view)
    }

    override fun onBindViewHolder(holder: FilmlerVH, position: Int) {
        holder.itemView.FilmTxt.text=filmler[position].filmAdi
        holder.itemView.filmImage.downloadImage(filmler[position].filmUrl,holder.itemView.context)
        holder.itemView.filmImage.setOnClickListener {
            val action = FilmFragmentDirections.actionFilmFragmentToFilmDetayFragment(filmler[position].filmId,filmler[position].filmAdi)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return filmler.size
    }
}