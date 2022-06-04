package com.didoszak.notepadmvvm.feature_note.presentation.screen_notes

import com.didoszak.notepadmvvm.feature_note.domain.model.Note
import com.didoszak.notepadmvvm.feature_note.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    object HideSorting: NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent()
    data class ShowNote(val id: Int): NotesEvent()
}
