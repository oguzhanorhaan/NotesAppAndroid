package com.task.noteapp.repository

import com.task.noteapp.db.dao.NoteDao
import com.task.noteapp.db.model.Note

class NotesRepository(private val noteDao: NoteDao) {
    fun getNoteById(id: Int) = noteDao.getNoteById(id)
    fun insertNote(note: Note) = noteDao.insertNote(note)
    fun deleteNote(note: Note) = noteDao.deleteNote(note)
    fun updateNote(note: Note) = noteDao.updateNote(note)
    fun getNotes()  = noteDao.getAllNotes()
}