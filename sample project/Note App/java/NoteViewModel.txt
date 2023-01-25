package com.mwi7.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes : LiveData<List<Note>>
    val repository  : NoteRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDoa()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note : Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNode(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}