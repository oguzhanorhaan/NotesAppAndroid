package com.task.noteapp.db.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.task.noteapp.scenes.note_edit.model.NoteRoute
import com.task.noteapp.scenes.note_edit.model.Route
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idNote")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "noteContent")
    var noteContent: String = "",

    @ColumnInfo(name = "imageUrl")
    var imageUrl: String = "",

    @ColumnInfo(name = "createdDate")
    var createdDate: String = "",

): Parcelable {
    var route: Route = Route.ADD
}
