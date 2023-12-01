package com.example.resepbela.ui.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.resepbela.R
import com.example.resepbela.model.Resep
import com.example.resepbela.ui.theme.SubmissionComposeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeDataResep = Resep(
        id = 0,
        name = "Rendang",
        image = R.drawable.foto1,
        description = "Bahan:\n\n500 gram daging sapi, potong 2 cm\n500 ml santan dari 1 butir kelapa (encerr)\n2000 ml santan dari 1 butir kelapa (kental)\n3 cm lengkuas, dimemarkan\n2 batang serai, diambil bagian putih dan memarkan\n2 lembar daun kunyit, dibuat simpul\n\nBumbu yang dihaluskan:\n\n12 buah cabai merah keriting\n5 buah cabai merah besar, buang bijinya\n12 butir bawang merah\n4 siung bawang putih\n4 butir kemiri, disangrai\n½ sdt merica butiran\n1 sdm ketumbar butiran\n3 sdt garam\n½ sdt gula pasir\n3 cm jahe\n\nCara membuat:\n\nPotong daging menjadi ukuran 2 cm.\nRebus santan encer dengan lengkuas, serai, daun kunyit, dan bumbu halus.\nMasukkan potongan daging, masak hingga empuk.\nTambahkan santan kental, masak hingga kuah mengental, berminyak, dan matang.",
        rating = 5.0,
        isFavorite = false
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            SubmissionComposeTheme {
                DetailInformation(
                    id = fakeDataResep.id,
                    name = fakeDataResep.name,
                    image = fakeDataResep.image,
                    description = fakeDataResep.description,
                    rating = fakeDataResep.rating,
                    isFavorite = fakeDataResep.isFavorite,
                    navigateBack = {},
                    onFavoriteButtonClicked = {_, _ ->}
                )
            }
        }
    }

    @Test
    fun detailInformation_isDisplayed() {
        composeTestRule.onNodeWithTag("scrollToBottom").performTouchInput {
            swipeUp()
        }
        composeTestRule.onNodeWithText(fakeDataResep.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeDataResep.description).assertIsDisplayed()
    }

    @Test
    fun addToFavoriteButton_hasClickAction() {
        composeTestRule.onNodeWithTag("favorite_detail_button").assertHasClickAction()
    }

    @Test
    fun detailInformation_isScrollable() {
        composeTestRule.onNodeWithTag("scrollToBottom").performTouchInput {
            swipeUp()
        }
    }

    @Test
    fun favoriteButton_hasCorrectStatus() {
        // Assert that the favorite button is displayed
        composeTestRule.onNodeWithTag("favorite_detail_button").assertIsDisplayed()

        // Assert that the content description of the favorite button is correct based on the isFavorite state
        val isFavorite = fakeDataResep.isFavorite // Set the isFavorite state here
        val expectedContentDescription = if (isFavorite) {
            "Remove from Favorite"
        } else {
            "Add to Favorite"
        }

        composeTestRule.onNodeWithTag("favorite_detail_button")
            .assertContentDescriptionEquals(expectedContentDescription)
    }
}