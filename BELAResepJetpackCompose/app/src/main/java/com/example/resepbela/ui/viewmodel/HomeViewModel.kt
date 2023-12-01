package com.example.resepbela.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resepbela.data.ResepRepository
import com.example.resepbela.model.Resep
import com.example.resepbela.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ResepRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Resep>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Resep>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) = viewModelScope.launch {
        _query.value = newQuery
        repository.searchResep(_query.value)
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }

    fun updateResep(id: Int, newState: Boolean) = viewModelScope.launch {
        repository.updateResep(id, newState)
            .collect { isUpdated ->
                if (isUpdated) search(_query.value)
            }
    }
}