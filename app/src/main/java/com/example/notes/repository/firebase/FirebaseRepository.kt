package com.example.notes.repository.firebase

import androidx.lifecycle.LiveData
import com.example.notes.model.AppNote
import com.example.notes.repository.Repository
import com.example.notes.utilits.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FirebaseRepository:Repository {
    init {
        AUTH = FirebaseAuth.getInstance()
    }

    override val allNotes: LiveData<List<AppNote>> = AllNoteLiveData()
    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun update(note: AppNote, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener {
                initRef()
                onSuccess()
            }
            .addOnFailureListener {
                AUTH.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener {
                        initRef()
                        onSuccess()
                    }
                    .addOnFailureListener {
                        onFail(it.message.toString())
                    }
            }
    }

    override fun signOut(onSuccess: () -> Unit) {
        super.signOut(onSuccess)
    }

    private fun initRef(){
        CURRENT_ID = AUTH.currentUser?.uid.toString()
        REF_DATABASE = FirebaseDatabase.getInstance().reference.child(CURRENT_ID)
    }
}