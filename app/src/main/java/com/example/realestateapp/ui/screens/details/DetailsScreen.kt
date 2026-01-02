package com.example.realestateapp.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.realestateapp.R
import com.example.realestateapp.domain.model.Listing
import com.example.realestateapp.ui.common.UiState

@Composable
@Preview
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {}
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
                DetailsContent(listing = state.data, onBackClick = onBackClick)
            }
        }
    }
}

@Composable
fun DetailsContent(listing: Listing, onBackClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = listing.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .height(200.dp),
                contentScale = ContentScale.Fit,
                error = painterResource(id = android.R.drawable.stat_notify_error),
                placeholder = painterResource(id = R.drawable.ic_placeholder)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = listing.propertyType ?: "Property",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "${listing.price ?: "N/A"} €",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = listing.city ?: "Unknown City",
                    style = MaterialTheme.typography.bodyLarge
                )

                Divider(modifier = Modifier.padding(vertical = 16.dp))

                DetailRow(label = "Rooms", value = listing.rooms?.toString())
                DetailRow(label = "Bedrooms", value = listing.bedrooms?.toString())
                DetailRow(label = "Area", value = listing.area?.let { "$it m²" })
                DetailRow(label = "Professional", value = listing.professional)
            }
        }

        // Back Button
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.surface.copy(
                    alpha = 0.7f
                )
            )
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        }
    }
}

@Composable
fun DetailRow(label: String, value: String?) {
    if (value != null) {
        Row(modifier = Modifier.padding(vertical = 4.dp)) {
            Text(
                text = "$label: ",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
