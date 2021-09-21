package com.example.appprueba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.appprueba.UserAplicacation.Companion.prefs
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    fun initUI(){
        close.setOnClickListener {
            prefs.close()
            onBackPressed()
        }
        val username = prefs.getName()
        txt.text = "Bienvenido $username"

        if (prefs.getState()){
            cambiarcolor()
        }
    }
    fun cambiarcolor(){
        container.setBackgroundColor(ContextCompat.getColor(this, R.color.efe))
    }
}