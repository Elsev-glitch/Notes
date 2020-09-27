package com.example.notes.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notes.repository.room.AppRoomDatabase
import com.example.notes.repository.room.AppRoomRepository
import com.example.notes.utilits.REPOSITORY
import com.example.notes.utilits.TYPE_ROOM

class StartViewModel(application: Application):AndroidViewModel(application) {
    private val mContext = application
    fun initRepository(type:String, onSuccess:()->Unit){
        when(type){
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
        }
    }
}