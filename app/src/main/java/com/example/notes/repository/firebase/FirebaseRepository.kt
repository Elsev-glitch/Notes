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

    override val allNotes: LiveData<List<AppNote>>
        get() = AllNoteLiveData()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        var newNote = mutableMapOf<String, Any>()
        val idFirebase = REF_DATABASE.push().key.toString()
        newNote.put(ID_FIREBASE, idFirebase)
        newNote.put(NAME, note.name)
        newNote.put(TEXT, note.text)
        REF_DATABASE.child(idFirebase).setValue(newNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override suspend fun update(note: AppNote, onSuccess: () -> Unit) {
        var newNote = mutableMapOf<String, Any>()
        newNote.put(ID_FIREBASE, note.idFirebase)
        newNote.put(NAME, note.name)
        newNote.put(TEXT, note.text)
        REF_DATABASE.child(note.idFirebase).updateChildren(newNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        REF_DATABASE.child(note.idFirebase).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
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
        AUTH.signOut()
    }

    private fun initRef(){
        CURRENT_ID = AUTH.currentUser?.uid.toString()
        REF_DATABASE = FirebaseDatabase.getInstance().reference.child(CURRENT_ID)
    }
}