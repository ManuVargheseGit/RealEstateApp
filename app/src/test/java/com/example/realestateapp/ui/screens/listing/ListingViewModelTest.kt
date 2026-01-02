package com.example.realestateapp.ui.screens.listing

import com.example.realestateapp.domain.model.Listing
import com.example.realestateapp.data.repository.RealEstateRepository
import com.example.realestateapp.domain.usecase.GetListingsUseCase
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

class ListingViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getListingsUseCase: GetListingsUseCase = mockk()
    private lateinit var viewModel: ListingViewModel

    @Test
    fun `uiState is initially Loading`() = runTest {
        // Given
        coEvery { getListingsUseCase() } returns emptyList()

        // When
        viewModel = ListingViewModel(getListingsUseCase)

        // Then
    }

    @Test
    fun `fetchListings emits Success when usecase returns data`() = runTest {
        // Given
        val mockListings = listOf(
            Listing(1, "Paris", 1000.0, 50.0, null, 2, 3, "Pro", "House", 1)
        )
        coEvery { getListingsUseCase() } returns mockListings

        // When
        viewModel = ListingViewModel(getListingsUseCase)

        // Then
        val currentState = viewModel.uiState.value
        assertTrue(currentState is UiState.Success)
        assertEquals(mockListings, (currentState as UiState.Success).data)
    }

    @Test
    fun `fetchListings emits Error when usecase throws exception`() = runTest {
        // Given
        coEvery { getListingsUseCase() } throws IOException("Network error")

        // When
        viewModel = ListingViewModel(getListingsUseCase)

        // Then
        val currentState = viewModel.uiState.value
        assertTrue(currentState is UiState.Error)
        assertEquals("Network Error: Network error", (currentState as UiState.Error).message)
    }
}
