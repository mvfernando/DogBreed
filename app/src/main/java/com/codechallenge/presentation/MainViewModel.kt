package com.codechallenge.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.codechallenge.core.di.AppModule
import com.codechallenge.domain.repository.DogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: DogRepository
) : ViewModel() {

    private val _state = MutableStateFlow<MainState>(MainState.Loading)
    val state: StateFlow<MainState> = _state

    fun fetchBreeds() {
        viewModelScope.launch {
            _state.update { MainState.Loading }
            delay(TIME_TO_FETCH_DATA)
            try {
                repository.getDogBreeds()
                    .asFlow()
                    .flowOn(Dispatchers.IO)
                    .map { breed ->
                        val image = getBreedImage(breed.name)
                        breed.copy(imageUrl = image)
                    }.collect { item ->
                        val breedList = when (val currentState = state.value) {
                            is MainState.Success -> currentState.breeds
                            else -> emptyList()
                        }
                        _state.update {
                            MainState.Success(breedList + item)
                        }
                    }
            } catch (exception: Exception) {
                _state.update {
                    MainState.Error(
                        message = exception.message
                            ?: ERROR_DEFAULT_MESSAGE
                    )
                }
            }
        }
    }

    private suspend fun getBreedImage(name: String): String? {
        return try {
            repository.getDogBreedImage(name)
        } catch (_: Exception) {
            null
        }
    }

    companion object {
        private const val TIME_TO_FETCH_DATA = 500L
        private const val ERROR_DEFAULT_MESSAGE = "Unknown error"

        fun create() = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(AppModule.dogRepository) as T
            }
        }
    }
}