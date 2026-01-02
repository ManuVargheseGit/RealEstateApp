package com.example.realestateapp.domain.usecase

import com.example.realestateapp.data.repository.RealEstateRepository
import com.example.realestateapp.domain.model.Listing
import javax.inject.Inject

class GetListingDetailUseCase @Inject constructor(
    private val repository: RealEstateRepository
) {
    suspend operator fun invoke(id: Int): Listing {
        return repository.getListingDetail(id)
    }
}
