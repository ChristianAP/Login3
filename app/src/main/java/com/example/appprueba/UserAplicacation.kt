package com.example.appprueba

import android.app.Application

class UserAplicacation:Application() {
    companion object{
        lateinit var prefs: Prefs
    }
    override fun onCreate() {
        super.onCreate()
         prefs = Prefs(applicationContext)
    }
}