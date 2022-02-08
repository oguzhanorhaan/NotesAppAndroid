package com.task.noteapp.scenes.note_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.task.noteapp.R
import com.task.noteapp.databinding.FragmentNoteEditBinding
import com.task.noteapp.db.model.Note
import com.task.noteapp.scenes.note_edit.model.Route
import com.task.noteapp.shared.injectFeature
import com.task.noteapp.shared.view.input_view.InputState
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.*

class NoteEditFragment : Fragment()  {

    private val viewModel by inject<NoteEditVM>() { parametersOf(this.activity?.applicationContext) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentNoteEditBinding.inflate(inflater)
        injectFeature()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setupView(binding)
        return binding.root
    }

    private fun setupView(binding: FragmentNoteEditBinding) {
        val noteInfo = NoteEditFragmentArgs.fromBundle(requireArguments()).noteInfo

        noteInfo.note?.let {
            binding.titleInputView.inputValue = it.title
            binding.messageInputView.inputValue = it.noteContent
            binding.imageUrlInputView.inputValue = it.imageUrl
        }

        binding.saveNoteButton.setOnClickListener {

            if (binding.titleInputView.inputValue.isNullOrEmpty() || binding.messageInputView.inputValue.isNullOrEmpty()) {
                Toast.makeText(this.activity, getString(R.string.fill_blanks),Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else if(binding.imageUrlInputView.inputState == InputState.Invalid) {
                Toast.makeText(this.activity, getString(R.string.validation_error), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            when(noteInfo.route.value) {
                Route.ADD.value -> {
                    viewModel.addNote(
                        Note().apply {
                            this.title = binding.titleInputView.inputValue.toString()
                            this.noteContent = binding.messageInputView.inputValue.toString()
                            this.imageUrl = binding.imageUrlInputView.inputValue.toString()
                            this.createdDate = SimpleDateFormat(getString(R.string.date_month_year_format), Locale.getDefault()).format(Date())
                        }
                    )
                }
                Route.EDIT.value -> {
                    noteInfo.note?.apply {
                        this.title = binding.titleInputView.inputValue.toString()
                        this.noteContent = binding.messageInputView.inputValue.toString()
                        this.imageUrl = binding.imageUrlInputView.inputValue.toString()
                        this.route = Route.EDIT
                    }
                    viewModel.updateNote(noteInfo.note)
                }
            }

            viewModel.navigateToMain.observe(this.viewLifecycleOwner,  {
                it?.let {
                    if (it) {
                        this.findNavController().navigate(NoteEditFragmentDirections.actionNoteEditFragmentToNoteListFragment())
                    }
                }
            })
        }
    }
}