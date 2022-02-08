package com.task.noteapp.shared.view

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(context: Context) : ViewModel(),
    CoroutineScope {

    private val _navigateToMain = MutableLiveData<Boolean>()

    val navigateToMain: LiveData<Boolean>
        get() = _navigateToMain

    fun navigateToMain() {
        _navigateToMain.value = true
    }

    private val job = Job()

    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
        _navigateToMain.value = false
    }
}