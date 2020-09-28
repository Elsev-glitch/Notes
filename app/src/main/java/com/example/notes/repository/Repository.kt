package com.example.notes.repository

import androidx.lifecycle.LiveData
import com.example.notes.model.AppNote

interface Repository {
    val allNotes:LiveData<List<AppNote>>

    suspend fun insert(note:AppNote, onSuccess:()->Unit)

    suspend fun update(note: AppNote, onSuccess: () -> Unit)

    suspend fun delete(note: AppNote, onSuccess: () -> Unit)

    fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit){}

    fun signOut(onSuccess: () -> Unit){}
}