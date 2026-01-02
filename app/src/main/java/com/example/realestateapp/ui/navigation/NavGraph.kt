package com.example.realestateapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.realestateapp.ui.screens.details.DetailsScreen
import com.example.realestateapp.ui.screens.listing.ListingScreen

@Composable
fun RealEstateNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RealEstateDestinations.LISTING_ROUTE
    ) {
        composable(RealEstateDestinations.LISTING_ROUTE) {
            ListingScreen(
                onListingClick = { listingId ->
                    navController.navigate("details/$listingId")
                }
            )
        }
        composable(
            route = RealEstateDestinations.DETAILS_ROUTE,
            arguments = listOf(navArgument(RealEstateDestinations.DETAILS_ARG_LISTING_ID) { type = NavType.IntType })
        ) {
            DetailsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
