package com.task.noteapp.scenes.note_details

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.noteapp.db.model.Note
import com.task.noteapp.repository.NotesRepository
import com.task.noteapp.shared.view.BaseViewModel
import kotlinx.coroutines.launch

class NoteDetailsVM(private val noteId: Integer,
                    val context: Context,
                    val notesRepository: NotesRepository): BaseViewModel(context) {

    private val _selectedItemId = MutableLiveData<Integer>()

    private val _selectedItem = MutableLiveData<Note>()

    val selectedItem: LiveData<Note>
        get() = _selectedItem

    init {
        _selectedItemId.value = noteId
        getSelectedNoteDetails()
    }

    private fun getSelectedNoteDetails() {
        launch {
            _selectedItem.value = _selectedItemId.value?.toInt()?.let {
                notesRepository.getNoteById(it)
            }
        }
    }

    fun deleteNote() {
        launch {
            _selectedItem.value?.let {
                notesRepository.deleteNote(it)
                navigateToMain()
            }
        }
    }
}
