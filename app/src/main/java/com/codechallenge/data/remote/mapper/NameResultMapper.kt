package com.codechallenge.data.remote.mapper

import com.codechallenge.core.domain.model.DogBreed

fun String.toDogBreed(): DogBreed {
    return DogBreed(
        name = this,
        imageUrl = null
    )
}