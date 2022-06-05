package com.didoszak.notepadmvvm.feature_note.presentation.screen_add_edit_note

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
