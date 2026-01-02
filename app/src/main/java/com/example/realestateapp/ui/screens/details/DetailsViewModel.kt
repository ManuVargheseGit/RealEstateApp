package com.example.realestateapp.ui.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestateapp.domain.model.Listing
import com.example.realestateapp.domain.usecase.GetListingDetailUseCase
import com.example.realestateapp.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getListingDetailUseCase: GetListingDetailUseCase
) : ViewModel() {

    private val listingId: Int = checkNotNull(savedStateHandle.get<Int>("listingId"))

    private val _uiState = MutableStateFlow<UiState<Listing>>(UiState.Loading)
    val uiState: StateFlow<UiState<Listing>> = _uiState.asStateFlow()

    init {
        fetchExampleDetails()
    }

    fun fetchExampleDetails() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val detail = getListingDetailUseCase(listingId)
                _uiState.value = UiState.Success(detail)
            } catch (e: IOException) {
                _uiState.value = UiState.Error("Network Error: ${e.message}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("An unexpected error occurred")
            }
        }
    }
}
