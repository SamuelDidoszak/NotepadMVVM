package com.didoszak.notepadmvvm.feature_note.domain.use_case

import com.didoszak.notepadmvvm.feature_note.domain.model.InvalidNoteException
import com.didoszak.notepadmvvm.feature_note.domain.model.Note
import com.didoszak.notepadmvvm.feature_note.domain.repository.NoteRepository

class AddNote(
    private val noteRepository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if(note.title.isBlank()) {
            throw InvalidNoteException("The title is empty")
        }
        noteRepository.insertNote(note)
    }
}