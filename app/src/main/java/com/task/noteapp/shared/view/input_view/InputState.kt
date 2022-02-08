package com.task.noteapp.shared.view.input_view

import android.view.View
import com.task.noteapp.R

enum class InputState(val backgroundResId: Int, val errorVisibilityState: Int) {
    Default(R.drawable.input_view_background, View.INVISIBLE),
    Valid(R.drawable.input_view_background, View.INVISIBLE),
    Invalid(R.drawable.input_view_error_background, View.VISIBLE)
}