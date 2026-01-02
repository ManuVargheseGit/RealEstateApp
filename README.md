# Real Estate Android App

A native Android application written in Kotlin using Jetpack Compose and MVVM architecture.

## Overview

The app displays a list of real estate properties fetched from a JSON API. Users can view details for each property.

### Screens
1. **Listing Screen**: Displays a scrollable list of properties with images, price, and type.
2. **Details Screen**: Shows full details of a selected property.

## Architecture

- **MVVM (Model-View-ViewModel)**: User Interface leads separation from business logic.
- **Jetpack Compose**: Modern UI toolkit for building native UI.
- **Repository Pattern**: Mediates between the ViewModel and the Data Source (Retrofit).
- **Dependency Injection**: Dagger Hilt for standard, scalable dependency management.

## Libraries Setup

- **Kotlin**: 1.9.20
- **Jetpack Compose**: BOM 2023.08.00
- **Retrofit**: 2.9.0 (Networking)
- **Moshi**: 1.15.0 (JSON Parsing)
- **Coil**: 2.5.0 (Image Element)
- **Navigation Compose**: 2.7.5

- **Dagger Hilt**: 2.48 (Dependency Injection)

## Project Structure

```
com.example.realestateapp
├── RealEstateApplication.kt    // Hilt Application
├── MainActivity.kt             // Entry point
├── di
│   └── NetworkModule.kt        // Hilt Module
├── data
│   ├── model                   // Data classes (Listing)
│   ├── remote                  // Retrofit API
│   └── repository              // Data Repository
└── ui
    ├── common                  // Shared UI classes (UiState)
    ├── navigation              // NavGraph & Destinations
    ├── screens
    │   ├── listing             // Listing Screen & ViewModel
    │   └── details             // Details Screen & ViewModel
    └── theme                   // Compose Theme
```

## How to Run

1. Open this folder `e:/task_android/RealEstateApp` in **Android Studio**.
2. Sync Project with Gradle Files.
3. Select an emulator or device.
4. Run the application (`Shift + F10`).

Note: Since the Gradle Wrapper scripts (`gradlew`) were not generated in this environment, opening in Android Studio will automatically generate them.
