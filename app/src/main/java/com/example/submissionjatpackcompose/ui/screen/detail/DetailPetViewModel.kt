package com.example.submissionjatpackcompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionjatpackcompose.data.PetRepository
import com.example.submissionjatpackcompose.model.FavoritePet
import com.example.submissionjatpackcompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailPetViewModel(private val repository: PetRepository): ViewModel() {
private  val _uiState: MutableStateFlow<UiState<FavoritePet>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<FavoritePet>> get() = _uiState

    fun getPetById(petId: Long){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getPetById(petId))
        }
    }
}