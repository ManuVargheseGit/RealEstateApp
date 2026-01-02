package com.example.realestateapp.ui.screens.listing

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.realestateapp.R
import com.example.realestateapp.domain.model.Listing
import com.example.realestateapp.ui.common.UiState


@Composable
fun ListingScreen(
    onListingClick: (Int) -> Unit,
    viewModel: ListingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is UiState.Error -> {
                Text(
                    text = state.message,
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error
                )
            }

            is UiState.Success -> {
                ListingList(listings = state.data, onListingClick = onListingClick)
            }
        }
    }
}

@Composable
fun ListingList(
    listings: List<Listing>,
    onListingClick: (Int) -> Unit
) {
    LazyColumn {
        items(listings) { listing ->
            ListingItem(listing = listing, onClick = { onListingClick(listing.id) })
        }
    }
}

@Composable
fun ListingItem(
    listing: Listing,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            AsyncImage(
                model = listing.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(androidx.compose.ui.graphics.Color.LightGray)
                    .height(200.dp),
                contentScale = ContentScale.Fit,
                error = painterResource(id = android.R.drawable.stat_notify_error),
                placeholder = painterResource(id = R.drawable.ic_placeholder)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = listing.propertyType ?: "Property",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${listing.price ?: "N/A"} €",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = listing.city ?: "Unknown City",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    listing.rooms?.let {
                        Text(
                            text = "$it Rooms • ",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    listing.bedrooms?.let {
                        Text(
                            text = "$it Bedrooms • ",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    listing.area?.let {
                        Text(
                            text = "$it m²",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}
