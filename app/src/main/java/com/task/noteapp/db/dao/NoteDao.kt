package com.task.noteapp.db.dao

import androidx.room.*
import com.task.noteapp.db.model.Note

@Dao
interface NoteDao {
    @Query("select * from notes")
    fun getAllNotes(): List<Note>

    @Query("select * from notes where idNote in (:id)")
    fun getNoteById(id: Int?): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Update(entity = Note::class)
    fun updateNote(note: Note)

    @Delete(entity = Note::class)
    fun deleteNote(note: Note)
}