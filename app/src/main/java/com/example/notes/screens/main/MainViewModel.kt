package com.example.notes.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notes.utilits.REPOSITORY

class MainViewModel(application: Application):AndroidViewModel(application) {
    val allNote = REPOSITORY.allNotes
}