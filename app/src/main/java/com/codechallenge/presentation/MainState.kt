package com.codechallenge.presentation

import com.codechallenge.core.domain.model.DogBreed


sealed class MainState {
    data object Loading : MainState()
    data class Success(val breeds: List<DogBreed>) : MainState()
    data class Error(val message: String) : MainState()
}