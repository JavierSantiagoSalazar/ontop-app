package com.example.ontopapp.ui.password

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.ontopapp.ui.common.copyToClipboard
import com.example.ontopapp.ui.common.generatePassword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PasswordViewModel : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun onGeneratePasswordButtonPressed() {
        val generatedPassword = generatePassword()
        _state.value = _state.value.copy(password = generatedPassword)
    }

    fun copyPassword(context: Context?){
        context?.copyToClipboard(_state.value.password)
    }

    data class UiState(
        val password: String = "",
    )
}
