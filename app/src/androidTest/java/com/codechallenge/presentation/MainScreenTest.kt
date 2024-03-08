package com.codechallenge.presentation

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.core.app.ApplicationProvider
import com.codechallenge.R
import com.codechallenge.data.FakeDogRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class MainScreenTest {

    private val repository = FakeDogRepository()

    @get:Rule
    val composeTestRule = createComposeRule()
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun fetchDogBreedsSuccessfully() {
        val viewModel = MainViewModel(repository)
        composeTestRule.setContent {
            MainScreen(viewModel = viewModel)
        }

        composeTestRule
            .onNodeWithTag(context.getString(R.string.progress))
            .assertExists()
            .assertIsDisplayed()

        runBlocking {
            delay(500)
        }

        composeTestRule
            .onNodeWithTag(context.getString(R.string.dog_breeds))
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun fetchDogBreedsWithError() {
        repository.setAnError(true)
        val viewModel = MainViewModel(repository)
        composeTestRule.setContent {
            MainScreen(viewModel = viewModel)
        }

        composeTestRule
            .onNodeWithTag(context.getString(R.string.progress))
            .assertExists()
            .assertIsDisplayed()

        runBlocking {
            delay(500)
        }

        composeTestRule
            .onNodeWithTag(context.getString(R.string.error))
            .assertExists()
            .assertIsDisplayed()
    }

}