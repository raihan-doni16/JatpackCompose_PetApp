package com.example.submissionjatpackcompose.di

import com.example.submissionjatpackcompose.data.PetRepository

object Injection {
    fun provideRepository():PetRepository{
        return  PetRepository.getInstance()
    }
}