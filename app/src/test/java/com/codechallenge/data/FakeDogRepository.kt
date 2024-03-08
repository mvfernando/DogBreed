package com.codechallenge.data

import com.codechallenge.core.domain.model.DogBreed
import com.codechallenge.core.domain.repository.DogRepository

class FakeDogRepository : DogRepository {

    private var occurredAnError: Boolean = false

    fun setAnError(isError: Boolean) {
        occurredAnError = isError
    }

    override suspend fun getDogBreeds(): List<DogBreed> {
        if (occurredAnError) {
            throw Exception("")
        }

        return listOf(DogBreed("test", null))
    }

    override suspend fun getDogBreedImage(name: String): String? {
        return if (name.isBlank()) null else ""
    }
}