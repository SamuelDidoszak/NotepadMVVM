package com.didoszak.notepadmvvm.feature_note.presentation.screen_add_edit_note

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent {
    data class EnteredTitle(val title: String): AddEditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditNoteEvent()
    data class EnteredContent(val content: String): AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditNoteEvent()
    data class ChangedColor(val color: Int): AddEditNoteEvent()
    object SaveNote: AddEditNoteEvent()
}
