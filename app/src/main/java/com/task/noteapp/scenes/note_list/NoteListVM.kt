package com.task.noteapp.scenes.note_list

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.task.noteapp.db.model.Note
import com.task.noteapp.repository.NotesRepository
import com.task.noteapp.shared.view.BaseViewModel
import kotlinx.coroutines.launch

class NoteListVM(val context: Context, val notesRepository: NotesRepository): BaseViewModel(context)  {

    val items = MutableLiveData<List<Note>>()

    fun getNotesFromDb() {
        launch {
            items.value = notesRepository.getNotes().reversed()
        }
    }
}