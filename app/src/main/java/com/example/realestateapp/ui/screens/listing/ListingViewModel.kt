package com.example.realestateapp.ui.screens.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestateapp.domain.model.Listing
import com.example.realestateapp.domain.usecase.GetListingsUseCase
import com.example.realestateapp.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(
    private val getListingsUseCase: GetListingsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Listing>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Listing>>> = _uiState.asStateFlow()

    init {
        fetchListings()
    }

    fun fetchListings() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val listings = getListingsUseCase()
                _uiState.value = UiState.Success(listings)
            } catch (e: IOException) {
                _uiState.value = UiState.Error("Network Error: ${e.message}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("An unexpected error occurred")
            }
        }
    }
}
