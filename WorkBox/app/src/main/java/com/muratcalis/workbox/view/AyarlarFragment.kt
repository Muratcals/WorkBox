package com.muratcalis.workbox.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.muratcalis.workbox.R
import com.muratcalis.workbox.viewModel.AnaSayfaViewModel
import kotlinx.android.synthetic.main.fragment_ayarlar.*

class AyarlarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ayarlar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let{
         instagram.setOnClickListener {
             val url ="https://www.instagram.com/muratcals_/"
             val intent =Intent(Intent.ACTION_VIEW)
             intent.data= Uri.parse(url)
             startActivity(intent)
         }
            linkedin.setOnClickListener {
                val url ="https://www.linkedin.com/in/murat-%C3%A7al%C4%B1%C5%9F-315a561b4/"
                val intent =Intent(Intent.ACTION_VIEW)
                intent.data= Uri.parse(url)
                startActivity(intent)
            }
            bozuk_film.setOnClickListener {
                val action =AyarlarFragmentDirections.actionAyarlarFragment2ToBozukFilmFragment()
                Navigation.findNavController(it).navigate(action)
            }
            oneri.setOnClickListener {
                Toast.makeText(it.context,"Öneride bulunmak için Instagram ve ya Linkedin hesabından bana ulaşabilirsiniz",Toast.LENGTH_LONG).show()
            }
            mail.setOnClickListener {
                val to = "muratcalss2@gmail.com"
                val subject = "Öneri ve Şikayet"
                val mailTo = "mailto:" + to + "?&subject=" + Uri.encode(subject)
                val emailIntent = Intent(Intent.ACTION_VIEW)
                emailIntent.data = Uri.parse(mailTo)
                startActivity(emailIntent)
            }
        }
    }
}