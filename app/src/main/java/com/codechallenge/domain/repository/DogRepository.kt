package com.codechallenge.domain.repository

import com.codechallenge.domain.model.DogBreed

interface DogRepository {

    suspend fun getDogBreeds(): List<DogBreed>

    suspend fun getDogBreedImage(name: String): String?
    
}