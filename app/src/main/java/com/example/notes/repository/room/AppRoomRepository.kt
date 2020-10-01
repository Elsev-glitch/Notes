package com.example.notes.repository.room

import androidx.lifecycle.LiveData
import com.example.notes.model.AppNote
import com.example.notes.repository.Repository

class AppRoomRepository(private val appRoomDao: AppRoomDao):Repository {
    override val allNotes: LiveData<List<AppNote>>
        get() = appRoomDao.getAllNote()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.insert(note)
        onSuccess()
    }

    override suspend fun update(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.update(note)
        onSuccess()
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        appRoomDao.delete(note)
        onSuccess()
    }

    override fun signOut() {
    }
}