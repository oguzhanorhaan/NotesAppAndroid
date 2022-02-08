package com.task.noteapp.shared.binding_adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.task.noteapp.db.model.Note
import com.task.noteapp.scenes.note_list.NoteListAdapter

@BindingAdapter("listData")
fun RecyclerView.bindRecyclerView(data: List<Note>?) {
    val adapter = this.adapter as NoteListAdapter
    adapter.submitList(data)
}
