package com.example.submissionjatpackcompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submissionjatpackcompose.data.PetRepository
import com.example.submissionjatpackcompose.ui.screen.detail.DetailPetViewModel

import com.example.submissionjatpackcompose.ui.screen.home.HomeViewModel

class ViewModelFactory( private  val repository: PetRepository): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DetailPetViewModel::class.java)){
            return DetailPetViewModel(repository)as T
        }
        throw IllegalAccessException("Unknown ViewModel class: "+ modelClass.name)


    }
}