package com.example.resepbela.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resepbela.data.ResepRepository
import com.example.resepbela.model.Resep
import com.example.resepbela.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: ResepRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Resep>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Resep>>
        get() = _uiState

    fun getResepById(id: Int) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        _uiState.value = UiState.Success(repository.getResepById(id))
    }


    fun updateResep(id: Int, newState: Boolean) = viewModelScope.launch {
        repository.updateResep(id, !newState)
            .collect { isUpdated ->
                if (isUpdated) getResepById(id)
            }
    }

}