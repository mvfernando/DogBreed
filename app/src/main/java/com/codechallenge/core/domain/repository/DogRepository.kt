package com.codechallenge.core.domain.repository

import com.codechallenge.core.domain.model.DogBreed

interface DogRepository {

    suspend fun getDogBreeds(): List<DogBreed>

    suspend fun getDogBreedImage(name: String): String?
    
}