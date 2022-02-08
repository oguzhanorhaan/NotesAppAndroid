package com.task.noteapp.scenes.note_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.task.noteapp.databinding.FragmentNoteListBinding
import com.task.noteapp.scenes.note_edit.model.NoteRoute
import com.task.noteapp.scenes.note_edit.model.Route
import com.task.noteapp.shared.injectFeature
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class NoteListFragment : Fragment()  {

    private val viewModel by inject<NoteListVM>() { parametersOf(this.activity?.applicationContext) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentNoteListBinding.inflate(inflater)
        injectFeature()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.noteList.adapter = NoteListAdapter(NoteListAdapter.OnClickListener {
            this.findNavController().navigate(NoteListFragmentDirections.actionNoteListFragmentToNoteDetailsFragment(it.id))
        })

        binding.addNoteButton.setOnClickListener {
            this.findNavController().navigate(NoteListFragmentDirections.actionNoteListFragmentToNoteEditFragment(
                NoteRoute(Route.ADD)
            ))
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotesFromDb()
    }
}