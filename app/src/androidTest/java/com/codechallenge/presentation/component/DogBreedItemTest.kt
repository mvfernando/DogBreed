package com.codechallenge.presentation.component

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.codechallenge.R
import com.codechallenge.core.presentation.component.DogBreedItem
import com.codechallenge.core.domain.model.DogBreed
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DogBreedItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var context: Context

    @Before
    fun setUp() {
        composeTestRule.setContent {
            context = LocalContext.current
            DogBreedItem(
                dogBreed = DogBreed(
                    name = "test",
                    imageUrl = "test"
                )
            )
        }
    }

    @Test
    fun dogBreedItemComponentAppearOnlyText() {
        composeTestRule
            .onNodeWithText("test")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun dogBreedItemComponentAppearWithTextAndImage() {
        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithText("test")
            .assertExists()

        composeTestRule
            .onNodeWithTag(context.getString(R.string.dog_image))
            .assertExists()
    }

}