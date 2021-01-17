package com.example.notes.utilits

import com.example.notes.repository.Repository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

const val TYPE_ROOM = "room"
const val TYPE_FIREBASE = "firebase"
lateinit var REPOSITORY:Repository
lateinit var AUTH: FirebaseAuth
lateinit var REF_DATABASE: DatabaseReference
const val ID_FIREBASE = "idFirebase"
lateinit var CURRENT_ID:String
const val NAME = "name"
const val TEXT = "text"

lateinit var EMAIL:String
lateinit var PASSWORD:String
