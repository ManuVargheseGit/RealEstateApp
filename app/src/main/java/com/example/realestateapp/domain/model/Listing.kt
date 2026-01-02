package com.example.realestateapp.domain.model

data class Listing(
    val id: Int,
    val city: String,
    val price: Double,
    val area: Double,
    val imageUrl: String?,
    val bedrooms: Int,
    val rooms: Int,
    val professional: String,
    val propertyType: String,
    val offerType: Int
)
