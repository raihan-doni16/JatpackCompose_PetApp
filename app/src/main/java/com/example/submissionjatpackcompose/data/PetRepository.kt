package com.example.submissionjatpackcompose.data

import com.example.submissionjatpackcompose.model.FavoritePet
import com.example.submissionjatpackcompose.model.PetDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PetRepository {

    private  val favoritePet = mutableListOf<FavoritePet>()

    init {
        if (favoritePet.isEmpty()){
            PetDataSource.dummyPet.forEach{data ->
                favoritePet.add(FavoritePet(data))
            }
        }
    }

    fun getAllPet(): Flow<List<FavoritePet>>{
        return flowOf(favoritePet)
    }
    fun getPetById(petId:Long):FavoritePet{
        return  favoritePet.first{data ->
            data.pet.id == petId
        }
    }




    fun searchPet(query: String):Flow<List<FavoritePet>>{
        return flowOf(favoritePet.filter {
            it.pet.title.contains(query,ignoreCase = true)

        })
    }

    companion object{
        @Volatile
        private  var instance: PetRepository?= null

        fun getInstance(): PetRepository= instance?: synchronized(this){
            PetRepository().apply {
                instance = this
            }
        }
    }
}