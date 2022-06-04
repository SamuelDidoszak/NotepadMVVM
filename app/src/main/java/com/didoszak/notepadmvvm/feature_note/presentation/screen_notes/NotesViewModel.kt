package com.didoszak.notepadmvvm.feature_note.presentation.screen_notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.didoszak.notepadmvvm.feature_note.domain.model.Note
import com.didoszak.notepadmvvm.feature_note.domain.use_case.NoteUseCases
import com.didoszak.notepadmvvm.feature_note.domain.util.NoteOrder
import com.didoszak.notepadmvvm.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var deletedNote: Note? = null

    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when(event) {
            is NotesEvent.Order -> {
                if(state.value.noteOrder::class == event.noteOrder::class &&
                        state.value.noteOrder.orderType == event.noteOrder.orderType)
                    return
                getNotes(event.noteOrder)
            }
            is NotesEvent.HideSorting -> {
                _state.value = state.value.copy(
                    showSorting = !state.value.showSorting
                )
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    deletedNote = event.note
                    noteUseCases.deleteNote(event.note)
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(deletedNote ?: return@launch)
                    deletedNote = null
                }

            }
            is NotesEvent.ShowNote -> {

            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notesList = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }
}