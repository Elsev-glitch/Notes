package com.example.notes.repository.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.notes.model.AppNote

@Dao
interface AppRoomDao {
    @Query("SELECT * from notes_table")
    fun getAllNote():LiveData<List<AppNote>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note:AppNote)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(note: AppNote)

    @Delete
    suspend fun delete(note: AppNote)
}
