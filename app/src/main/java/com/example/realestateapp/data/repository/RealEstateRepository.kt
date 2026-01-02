package com.example.realestateapp.data.repository

import com.example.realestateapp.data.remote.RealEstateApi
import com.example.realestateapp.domain.model.Listing
import javax.inject.Inject

interface RealEstateRepository {
    suspend fun getListings(): List<Listing>
    suspend fun getListingDetail(id: Int): Listing
}

class RealEstateRepositoryImpl @Inject constructor(private val api: RealEstateApi) : RealEstateRepository {

    override suspend fun getListings(): List<Listing> {
        return api.getListings().items.map { it.toDomain() }
    }

    override suspend fun getListingDetail(id: Int): Listing {
        return api.getListingDetail(id).toDomain()
    }
}
