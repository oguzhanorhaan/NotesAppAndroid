package com.task.noteapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.noteapp.db.dao.NoteDao
import com.task.noteapp.db.model.Note

@Database(entities = [(Note::class)], version = 1, exportSchema = false)
abstract class NoteDb : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}