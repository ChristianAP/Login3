package com.example.appprueba

import android.content.Context

class Prefs(val context: Context) {
    val SHARED_NAME = "baseData"
    val SHARED_USER = "username"
    val SHARED_PASSWORD = "password"
    val SHARED_CHECK = "estate"
    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun save(name:String){
        storage.edit().putString(SHARED_USER, name).apply()
    }
    fun savepass(password:String){
        storage.edit().putString(SHARED_PASSWORD, password).apply()
    }
    fun savestate(state: Boolean){
        storage.edit().putBoolean(SHARED_CHECK, state).apply()
    }
    fun getName():String{
        return storage.getString(SHARED_USER, "")!!
    }

    fun getState():Boolean{
        return storage.getBoolean(SHARED_CHECK, false)
    }

    fun close(){
        storage.edit().clear().apply()
    }
}