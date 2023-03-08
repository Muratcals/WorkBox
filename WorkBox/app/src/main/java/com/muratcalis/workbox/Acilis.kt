package com.muratcalis.workbox

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.muratcalis.workbox.databinding.ActivityAcilisBinding

class Acilis : AppCompatActivity() {
    val splashScreen =2000
    private lateinit var  network :ConnectivityManager.NetworkCallback
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acilis)
        val binding =ActivityAcilisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val animasyon =AnimationUtils.loadAnimation(this,R.anim.animasyon)
        val imageView =binding.giris
        val isim =binding.girisText

        imageView.animation=animasyon
        isim.animation=animasyon

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.P){
            val network =applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (network.activeNetwork==null){
                val uyarı = AlertDialog.Builder(this)
                uyarı.setTitle("Internet bağlantı hatası")
                uyarı.setMessage("Lütfen internet bağlantınızı kontrol edip tekrar deneyiniz")
                uyarı.setPositiveButton("Tekrar dene", DialogInterface.OnClickListener { _, _ ->
                    val intent = Intent(this,Acilis::class.java)
                    startActivity(intent)
                    finish()
                })
                uyarı.setNegativeButton("Kapat",DialogInterface.OnClickListener { _, _ ->
                    finish()
                })
                uyarı.show()
            }else{
                @Suppress("DEPRECATION")
                Handler().postDelayed({
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                },splashScreen.toLong())
            }
        }
    }
}