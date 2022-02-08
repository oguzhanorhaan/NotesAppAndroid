package com.task.noteapp.scenes.note_edit

import android.content.Context
import com.task.noteapp.db.model.Note
import com.task.noteapp.repository.NotesRepository
import com.task.noteapp.shared.view.BaseViewModel
import kotlinx.coroutines.launch

class NoteEditVM(val context: Context, val notesRepository: NotesRepository): BaseViewModel(context) {

    fun addNote(note: Note) {
        saveToDb(note)
    }

    private fun saveToDb(vararg params: Note) {
        launch {
            notesRepository.insertNote(params[0])
            navigateToMain()
        }
    }


    fun updateNote(note: Note?) {
        launch {
            note?.let {
                notesRepository.updateNote(it)
            }
            navigateToMain()
        }
    }
}