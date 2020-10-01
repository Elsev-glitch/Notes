package com.example.notes.utilits

import android.content.Context
import android.content.SharedPreferences

object Preference {

    private lateinit var mPreferences: SharedPreferences
    const val NAME_PREFERENCE = "preference"
    const val INIT_USER ="inituser"
    const val TYPE_DATABASE = "typedatabase"

    fun getPreference(context: Context):SharedPreferences{
        mPreferences = context.getSharedPreferences(NAME_PREFERENCE, Context.MODE_PRIVATE)
        return mPreferences
    }

    fun setInitUser(init: Boolean){
        mPreferences.edit()
            .putBoolean(INIT_USER, init)
            .apply()
    }

    fun setDatabaseType(type:String){
        mPreferences.edit()
            .putString(TYPE_DATABASE, type)
            .apply()
    }

    fun getInitUser():Boolean{
        return mPreferences.getBoolean(INIT_USER, false)
    }

    fun getDatabaseType():String{
        return mPreferences.getString(TYPE_DATABASE, TYPE_ROOM).toString()
    }
}