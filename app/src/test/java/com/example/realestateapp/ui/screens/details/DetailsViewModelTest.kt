package com.example.realestateapp.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import com.example.realestateapp.data.model.Listing
import com.example.realestateapp.data.repository.RealEstateRepository
import com.example.realestateapp.ui.common.UiState
import com.example.realestateapp.utils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class DetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository: RealEstateRepository = mockk()
    private val savedStateHandle: SavedStateHandle = mockk(relaxed = true)
    private lateinit var viewModel: DetailsViewModel

    @Test
    fun `fetchExampleDetails emits Success when repository returns data`() = runTest {
        // Given
        val listingId = 1
        val mockListing = Listing(listingId, "Paris", 1000.0, 50.0, null, 2, 3, "Pro", "House", 1)
        
        coEvery { savedStateHandle.get<Int>("listingId") } returns listingId
        coEvery { repository.getListingDetail(listingId) } returns mockListing

        // When
        viewModel = DetailsViewModel(savedStateHandle, repository)

        // Then
        val currentState = viewModel.uiState.value
        assertTrue(currentState is UiState.Success)
        assertEquals(mockListing, (currentState as UiState.Success).data)
    }

    @Test
    fun `fetchExampleDetails emits Error when repository throws exception`() = runTest {
        // Given
        val listingId = 1
        coEvery { savedStateHandle.get<Int>("listingId") } returns listingId
        
        coEvery { repository.getListingDetail(listingId) } throws IOException("Network error")

        // When
        viewModel = DetailsViewModel(savedStateHandle, repository)

        // Then
        val currentState = viewModel.uiState.value
        assertTrue(currentState is UiState.Error)
        assertEquals("Network Error: Network error", (currentState as UiState.Error).message)
    }
}
