package com.example.ontopapp.ui.phrase

import androidx.lifecycle.ViewModel
import com.example.ontopapp.domain.Error
import com.example.ontopapp.ui.common.convertSentence
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PhraseViewModel : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun onButtonPressed(sentence: String) {
        if (sentence.isEmpty()) {
            _state.value = state.value.copy(sentence = "", convertedSentence = "", error = Error.EmptyEditText)
            _state.value = UiState()
        } else {
            _state.value =
                _state.value.copy(
                    sentence = sentence,
                    convertedSentence = convertSentence(sentence),
                    error = null
                )
        }
    }

    data class UiState(
        val sentence: String = "",
        val convertedSentence: String = "",
        val error: Error? = null,
    )
}
