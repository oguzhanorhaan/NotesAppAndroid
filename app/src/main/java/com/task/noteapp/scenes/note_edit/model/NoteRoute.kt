package com.task.noteapp.scenes.note_edit.model

import android.os.Parcelable
import com.task.noteapp.db.model.Note
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NoteRoute(val route: Route,val note: Note? = null): Parcelable