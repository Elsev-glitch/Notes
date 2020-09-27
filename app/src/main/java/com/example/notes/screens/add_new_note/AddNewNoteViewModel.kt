package com.example.notes.screens.add_new_note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.model.AppNote
import com.example.notes.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewNoteViewModel(application: Application):AndroidViewModel(application) {
    fun insert(note:AppNote, onSuccess:()->Unit){
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.insert(note){
                onSuccess()
            }
        }
    }
}