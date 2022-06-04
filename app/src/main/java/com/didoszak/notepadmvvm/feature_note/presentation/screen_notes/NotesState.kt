package com.didoszak.notepadmvvm.feature_note.presentation.screen_notes

import com.didoszak.notepadmvvm.feature_note.domain.model.Note
import com.didoszak.notepadmvvm.feature_note.domain.util.NoteOrder
import com.didoszak.notepadmvvm.feature_note.domain.util.OrderType

data class NotesState(
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val showSorting: Boolean = false,
    val notesList: List<Note> = emptyList()
)
