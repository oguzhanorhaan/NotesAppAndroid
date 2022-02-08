package com.task.noteapp.scenes.note_edit.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class Route(val value: Int): Parcelable {
    ADD(1),EDIT(2)
}