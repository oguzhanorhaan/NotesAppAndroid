package com.task.noteapp.shared.view.input_view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.task.noteapp.R
import com.task.noteapp.databinding.InputViewBinding
import com.task.noteapp.shared.extension.disposed
import com.task.noteapp.shared.extension.textChanges
import io.reactivex.rxjava3.disposables.CompositeDisposable

@SuppressLint("UseCompatLoadingForDrawables")
class InputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: InputViewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.input_view, this, true)
    private val disposeBag: CompositeDisposable = CompositeDisposable()

    var inputState: InputState = InputState.Default
        set(value) {
            field = value
            handleInputState()
        }

    var title: String? = null
        set(value) {
            field = value
            binding.titleTextView.text = value
        }

    var errorMessage: String? = null
        set(value) {
            field = value
            binding.errorTextView.text = value
        }

    var placeHolder: String? = null
        set(value) {
            field = value
            binding.inputEditText.hint = value
        }

    var inputFormat = InputFormat.None
        set(value) {
            field = value
        }

    var inputValue: String? = null
        get() {
            field = binding.inputEditText.text.toString()
            return field
        }
        set(value) {
            field = value
            binding.inputEditText.setText(value)
            binding.inputEditText.text?.lastIndex?.let { binding.inputEditText.setSelection(it + 1) }
        }

    init {
        listenInputChanges()
    }

    private fun listenInputChanges() {
        binding.inputEditText.textChanges()
            .subscribe {
                checkIsValidInput(it.text)
            }.disposed(by = disposeBag)
    }

    private fun checkIsValidInput(text: CharSequence?) {

        text.let {
            inputState = when {
                (inputFormat == InputFormat.None) || (it.isNullOrBlank()) || inputFormat.validate(
                    it.toString()
                ) ->
                    InputState.Valid
                it.isNullOrEmpty() ->
                    InputState.Default
                (inputFormat != InputFormat.None) ->
                    InputState.Invalid
                else ->
                    InputState.Default
            }
        }
    }

    fun refreshState() {
        checkIsValidInput(binding.inputEditText.text)
    }

    private fun handleInputState() {
        binding.errorTextView.visibility = inputState.errorVisibilityState
        binding.inputContainer.background = context.getDrawable(inputState.backgroundResId)
    }
}