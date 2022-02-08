package com.task.noteapp.shared.view.input_view

import com.task.noteapp.shared.extension.isTextValid
import com.task.noteapp.shared.extension.isUrl

enum class InputFormat {
    None, Url;

    fun validate(text: String): Boolean = when (this) {
        Url -> text.isUrl
        None -> text.isTextValid
    }
}