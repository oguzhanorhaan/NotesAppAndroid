package com.task.noteapp.shared.view.input_view

import androidx.databinding.BindingAdapter


@BindingAdapter("android:inputFormat")
fun setInputFormatType(view: InputView, type: InputFormat) {
    view.inputFormat = type
    view.refreshState()
}

@BindingAdapter("android:title")
fun setTitle(view: InputView, resId: Int) {
    view.title = view.context.getString(resId)
}

@BindingAdapter("android:errorMessage")
fun setErrorMessage(view: InputView, resId: Int) {
    view.errorMessage = view.context.getString(resId)
}

@BindingAdapter("android:placeHolder")
fun setPlaceHolder(view: InputView, resId: Int) {
    view.placeHolder = view.context.getString(resId)
}
