package com.codechallenge.data.repository

import com.codechallenge.data.remote.DogService
import com.codechallenge.data.remote.mapper.toDogBreed
import com.codechallenge.core.domain.model.DogBreed
import com.codechallenge.core.domain.repository.DogRepository

class DogRepositoryImpl(
    private val service: DogService
) : DogRepository {

    override suspend fun getDogBreeds(): List<DogBreed> {
        val response = service.getBreeds()
        return response.message.map { name ->
            name.toDogBreed()
        }
    }

    override suspend fun getDogBreedImage(name: String): String? {
        val image = service.getImage(name)
        return image?.message
    }

}