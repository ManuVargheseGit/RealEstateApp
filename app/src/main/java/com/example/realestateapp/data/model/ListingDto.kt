package com.example.realestateapp.data.model

import com.example.realestateapp.domain.model.Listing
import com.squareup.moshi.Json

data class ListingDto(
    @Json(name = "id") val id: Int?, // API might return nulls, safe to handle
    @Json(name = "city") val city: String?,
    @Json(name = "price") val price: Double?,
    @Json(name = "area") val area: Double?,
    @Json(name = "url") val url: String?,
    @Json(name = "bedrooms") val bedrooms: Int?,
    @Json(name = "rooms") val rooms: Int?,
    @Json(name = "professional") val professional: String?,
    @Json(name = "propertyType") val propertyType: String?,
    @Json(name = "offerType") val offerType: Int?
) {
    fun toDomain(): Listing {
        return Listing(
            id = id ?: 0,
            city = city ?: "Unknown",
            price = price ?: 0.0,
            area = area ?: 0.0,
            imageUrl = url,
            bedrooms = bedrooms ?: 0,
            rooms = rooms ?: 0,
            professional = professional ?: "",
            propertyType = propertyType ?: "",
            offerType = offerType ?: 0
        )
    }
}

data class ListingResponse(
    @Json(name = "items") val items: List<ListingDto>,
    @Json(name = "totalCount") val totalCount: Int
)
