package com.example.notes.repository.firebase

import androidx.lifecycle.LiveData
import com.example.notes.model.AppNote
import com.example.notes.utilits.REF_DATABASE
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class AllNoteLiveData : LiveData<List<AppNote>>(){
    private val listener = object : ValueEventListener{
        override fun onCancelled(p0: DatabaseError) {

        }

        override fun onDataChange(p0: DataSnapshot) {
            value = p0.children.map {
                it.getValue(AppNote::class.java) ?: AppNote()
            }
        }

    }

    override fun onActive() {
        REF_DATABASE.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        REF_DATABASE.removeEventListener(listener)
        super.onInactive()
    }
}