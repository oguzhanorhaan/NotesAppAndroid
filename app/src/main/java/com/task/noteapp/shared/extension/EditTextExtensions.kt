package com.task.noteapp.shared.extension

import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import io.reactivex.rxjava3.core.Observable

fun EditText.textChanges(): Observable<EditTextModel> {
    return Observable.create { emitter ->
        doOnTextChanged { text, start, before, count ->
            emitter.onNext(EditTextModel(text, start, before, count))
        }
    }
}

class EditTextModel(
    val text: CharSequence?,
    val start: Int,
    val before: Int,
    val count: Int
)