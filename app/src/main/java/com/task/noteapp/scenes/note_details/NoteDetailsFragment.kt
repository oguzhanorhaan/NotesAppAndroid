package com.task.noteapp.scenes.note_details

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.task.noteapp.R
import com.task.noteapp.databinding.FragmentNoteDetailsBinding
import com.task.noteapp.scenes.note_edit.NoteEditFragmentDirections
import com.task.noteapp.scenes.note_edit.model.NoteRoute
import com.task.noteapp.scenes.note_edit.model.Route
import com.task.noteapp.scenes.note_list.NoteListFragmentDirections
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class NoteDetailsFragment: Fragment() {

   var binding: FragmentNoteDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteDetailsBinding.inflate(inflater)
        binding?.lifecycleOwner = this
        val noteItemId = NoteDetailsFragmentArgs.fromBundle(requireArguments()).selectedItem
        val viewModel: NoteDetailsVM by inject<NoteDetailsVM> { parametersOf(Integer(noteItemId), this.activity?.applicationContext) }
        binding?.viewModel = viewModel
        setupView(binding!!)
        setHasOptionsMenu(true)
        return binding?.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when(item.itemId) {
            R.id.action_delete -> {
                binding?.viewModel?.deleteNote()
            }
            R.id.action_edit -> {
                this.findNavController().navigate(
                    NoteDetailsFragmentDirections.actionNoteDetailsFragmentToNoteEditFragment(
                    NoteRoute(Route.EDIT, binding?.viewModel?.selectedItem?.value)
                ))
            }
        }
        return true
    }

    private fun setupView(binding: FragmentNoteDetailsBinding) {
        binding.viewModel?.navigateToMain?.observe(this.viewLifecycleOwner, {
            it?.let {
                if (it) {
                    this.findNavController().navigate(NoteDetailsFragmentDirections.actionNoteDetailsFragmentToNoteListFragment())
                }
            }
        })
    }
}