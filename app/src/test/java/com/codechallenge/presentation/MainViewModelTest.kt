package com.codechallenge.presentation

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.codechallenge.core.test.TestDispatcherRule
import com.codechallenge.data.FakeDogRepository
import com.codechallenge.domain.model.DogBreed
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    private lateinit var repository: FakeDogRepository
    private lateinit var viewModel: MainViewModel

    @get: Rule
    val dispatcherRule = TestDispatcherRule()

    @Before
    fun setUp() {
        repository = FakeDogRepository()
    }

    @Test
    fun `When Successfully Fetch All Dog Breeds`() = runTest {
        viewModel = MainViewModel(repository)
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(MainState.Loading)

            viewModel.fetchBreeds()

            val state = awaitItem()
            assertThat(state).isEqualTo(
                MainState.Success(listOf(DogBreed("test", "")))
            )
        }
    }

    @Test
    fun `Fetch All Dog Breeds with Error`() = runTest {
        repository.setAnError(true)
        viewModel = MainViewModel(repository)
        viewModel.state.test {
            val initialState = awaitItem()
            assertThat(initialState).isEqualTo(MainState.Loading)

            viewModel.fetchBreeds()

            val state = awaitItem()
            assertThat(state).isEqualTo(MainState.Error(""))
        }
    }
}