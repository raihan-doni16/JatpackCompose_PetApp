package com.example.submissionjatpackcompose.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionjatpackcompose.data.PetRepository
import com.example.submissionjatpackcompose.model.FavoritePet
import com.example.submissionjatpackcompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: PetRepository):ViewModel() {

    private  val _uiState: MutableStateFlow<UiState<List<FavoritePet>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<FavoritePet>>> get() = _uiState

private  val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            try {
                val result = repository.searchPet(_query.value)
                    .map { data -> data.sortedBy { it.pet.title } }
                    .first()

                _uiState.value = UiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }
    fun getAllPet(){
        viewModelScope.launch {
            repository.getAllPet()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{ data ->
                    _uiState.value = UiState.Success(data)

                }
        }
    }
}