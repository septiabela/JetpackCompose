package com.example.resepbela.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resepbela.data.ResepRepository
import com.example.resepbela.model.Resep
import com.example.resepbela.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: ResepRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Resep>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Resep>>>
        get() = _uiState

    fun getFavoriteResep() = viewModelScope.launch {
        repository.getFavoriteResep(
        )
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }

    fun updateResep(id: Int, newState: Boolean) {
        repository.updateResep(id, newState)
        getFavoriteResep()
    }
}