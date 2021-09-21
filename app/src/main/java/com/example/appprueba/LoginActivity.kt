package com.example.appprueba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appprueba.UserAplicacation.Companion.prefs
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initUI()
        validar()
    }
    fun validar(){
        if (prefs.getName().isNotEmpty()){
            cambiarPestaña()
        }
    }
    fun initUI(){
        login.setOnClickListener{ accessDetail() }

    }

    fun accessDetail(){
        if(user.text.toString().isNotEmpty()){
            prefs.save(user.text.toString())
            prefs.savepass(pass.text.toString())
            prefs.savestate(caja.isChecked)
            cambiarPestaña()
        }else{

        }
    }

    fun cambiarPestaña(){
        startActivity(Intent(this,MainActivity:: class.java))
    }
}