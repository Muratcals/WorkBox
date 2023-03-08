package com.muratcalis.workbox.view

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.transition.Slide
import android.transition.TransitionManager
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.muratcalis.workbox.R
import com.muratcalis.workbox.adapter.AnaSayfaAdapter
import com.muratcalis.workbox.adapter.KapakRecyler
import com.muratcalis.workbox.model.Filmler
import com.muratcalis.workbox.viewModel.AnaSayfaViewModel
import kotlinx.android.synthetic.main.fragment_ana_sayfa.*
import java.sql.SQLDataException

class AnaSayfaFragment : Fragment() {
    var position =0
    var sure =10*3000
    var timer : CountDownTimer? =null
    var anaAdapter=AnaSayfaAdapter(arrayListOf())
    var kapakAdapter=KapakRecyler(arrayListOf())
    private lateinit var viewModel: AnaSayfaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ana_sayfa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        anaSayfaMenu.setBackgroundColor(resources.getColor(R.color.beyaz))
        position=0
        viewModel = ViewModelProvider(this).get(AnaSayfaViewModel::class.java)
        loadingData()
        timer =timer(kapak,sure)
        timer!!.start()
        @Suppress("DEPRECATION")
        anaSayfaMenu.setOnNavigationItemSelectedListener { item->
            when (item.itemId) {
                R.id.cok_izlenen -> {
                    // put your code here
                    viewModel.cokIzlenen(requireView())
                    viewModel.filmlerMutable.observe(viewLifecycleOwner, Observer { film->
                        val adptr =AnaSayfaAdapter(film as ArrayList<Filmler>)
                        filmRecyler.adapter=adptr
                        filmRecyler.layoutManager = GridLayoutManager(requireView().context,2)
                    })
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.yeni_cikanlar -> {
                    // put your code here
                    viewModel.yeniCikanlar(requireView())
                    viewModel.filmlerMutable.observe(viewLifecycleOwner, Observer { film->
                        val adptr =AnaSayfaAdapter(film as ArrayList<Filmler>)
                        filmRecyler.adapter=adptr
                        filmRecyler.layoutManager = GridLayoutManager(requireView().context,2)
                    })
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
    fun timer(kapakRecyler: RecyclerView,sure:Int) : CountDownTimer {

        return object : CountDownTimer(sure.toLong(),3000){
            override fun onTick(millisUntilFinished: Long) {
                kapakRecyler.scrollToPosition(position)
                position+= 1
            }
            override fun onFinish() {
                    position=0
                    timer!!.start()
            }
        }
    }
    override fun onStop() {
        super.onStop()
        timer!!.cancel()
    }

    fun loadingData(){
        viewModel.yeniCikanlar(requireView())
        viewModel.kapak(requireView())
        viewModel.kapakFilm.observe(viewLifecycleOwner, Observer { kapakk->
            kapakAdapter= KapakRecyler(kapakk as ArrayList<Filmler>)
            kapak.adapter=kapakAdapter
            kapak.layoutManager=LinearLayoutManager(requireView().context,LinearLayoutManager.HORIZONTAL,false)
        })
        viewModel.filmlerMutable.observe(viewLifecycleOwner, Observer {
            anaAdapter= AnaSayfaAdapter(it as ArrayList<Filmler>)
            filmRecyler.adapter=anaAdapter
            filmRecyler.layoutManager = GridLayoutManager(requireView().context,2)
        })

    }
}