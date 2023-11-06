package com.example.submissionjatpackcompose.ui.navigation

sealed class Screen (val route: String){

    object  Home: Screen("home")
    object  Profile: Screen("profile")
    object  DetailPet: Screen("home/{petId}"){
        fun createRoute(petId:Long) = "home/$petId"
    }
}