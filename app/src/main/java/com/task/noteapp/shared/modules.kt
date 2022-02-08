package com.task.noteapp.shared

import android.content.Context
import androidx.room.Room
import com.task.noteapp.db.NoteDb
import com.task.noteapp.repository.NotesRepository
import com.task.noteapp.scenes.note_details.NoteDetailsVM
import com.task.noteapp.scenes.note_edit.NoteEditVM
import com.task.noteapp.scenes.note_list.NoteListVM
import org.koin.android.ext.koin.androidApplication
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules( listOf(
        viewModelModule,
        repositoryModule,
        notesAppModule
    ))
}

val viewModelModule: Module = module {
    viewModel { (context: Context) -> NoteListVM(context = context, notesRepository = get()) }
    viewModel { (context: Context) -> NoteEditVM(context = context, notesRepository = get()) }
    viewModel { (id: Integer, context: Context) -> NoteDetailsVM(noteId = id, context = context, notesRepository = get()) }
}

val repositoryModule = module {
    single { NotesRepository(noteDao = get()) }
}

val notesAppModule = module {
    single { Room.databaseBuilder(
        androidApplication(), NoteDb::class.java, "notedatabase"
    ).allowMainThreadQueries().build() }

    single { get<NoteDb>().noteDao() }
}
