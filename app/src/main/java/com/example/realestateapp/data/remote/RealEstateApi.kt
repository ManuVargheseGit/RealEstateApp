package com.example.realestateapp.data.remote

import com.example.realestateapp.data.model.ListingDto
import com.example.realestateapp.data.model.ListingResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RealEstateApi {
    @GET("listings.json")
    suspend fun getListings(): ListingResponse

    @GET("listings/{listingId}.json")
    suspend fun getListingDetail(@Path("listingId") listingId: Int): ListingDto
}
