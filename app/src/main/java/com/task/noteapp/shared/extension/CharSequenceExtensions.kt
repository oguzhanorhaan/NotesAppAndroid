package com.task.noteapp.shared.extension

import android.webkit.URLUtil

val String?.isUrl: Boolean get() = !isNullOrBlank() && URLUtil.isValidUrl(this)
val String?.isTextValid: Boolean get() = !isNullOrBlank()