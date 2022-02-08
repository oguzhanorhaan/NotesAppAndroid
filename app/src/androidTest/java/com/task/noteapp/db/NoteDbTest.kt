package com.task.noteapp.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.task.noteapp.NoteApp
import com.task.noteapp.db.dao.NoteDao
import com.task.noteapp.db.model.Note
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class NoteDbTest : TestCase() {
    private lateinit var noteDao: NoteDao
    private lateinit var noteDb: NoteDb

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<NoteApp>()
        noteDb = Room.inMemoryDatabaseBuilder(
            context, NoteDb::class.java
        ).build()
        noteDao = noteDb.noteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        noteDb.close()
    }

    @Test
    fun writeAndReadNote() = runBlocking {
        val note = Note().apply {
            this.createdDate = "22/09/2021"
            this.title = "Test title"
            this.noteContent = "This is a test"
        }
        noteDao.insertNote(note)
        val notes = noteDao.getAllNotes()
        assertTrue(notes.contains(note))
    }
}