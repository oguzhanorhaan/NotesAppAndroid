package com.task.noteapp.scenes.note_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.task.noteapp.databinding.ListViewItemBinding
import com.task.noteapp.db.model.Note
import com.task.noteapp.scenes.note_edit.model.Route

class NoteListAdapter ( val onClickListener: OnClickListener) :
    ListAdapter<Note, NoteListAdapter.NoteItemViewHolder>(DiffCallback)  {

    class NoteItemViewHolder(private var binding: ListViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noteItem: Note) {
            binding.property = noteItem
            binding.isEditedTag.visibility = when(noteItem.route) {
                Route.EDIT -> View.VISIBLE
                else -> View.GONE
            }
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): NoteItemViewHolder {
       val binding = ListViewItemBinding.inflate(LayoutInflater.from(parent.context))
        return NoteItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }


    class OnClickListener(val clickListener: (note: Note) -> Unit) {
        fun onClick(item: Note) = clickListener(item)
    }
}