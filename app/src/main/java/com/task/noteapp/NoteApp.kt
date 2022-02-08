package com.task.noteapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NoteApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { androidContext(this@NoteApp) }
    }
}