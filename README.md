# Real Estate Android App

A native Android application written in Kotlin using **Jetpack Compose**, **Clean Architecture**, and **MVVM**.

## Overview

The app displays a list of real estate properties fetched from a JSON API. Users can view details for each property. The project demonstrates advanced architectural patterns suitable for large-scale, maintainable Android development.

### Screens
1. **Listing Screen**: Displays a scrollable list of properties with images, price, and type.
2. **Details Screen**: Shows full details of a selected property.

## Architecture

This project follows **Clean Architecture** principles to separate concerns, improve testability, and decouple the UI from the external data sources.

- **Presentation Layer (MVVM)**: 
  - **Jetpack Compose**: Declarative UI.
  - **ViewModels**: Hold UI state (`UiState`) and interact with Use Cases.
- **Domain Layer (Kotlin Pure)**:
  - **Use Cases**: Encapsulate reusable business logic (`GetListingsUseCase`).
  - **Models**: Pure Kotlin data classes, independent of frameworks.
- **Data Layer (Repository Pattern)**:
  - **Repositories**: Mediator between Domain and Data Sources.
  - **DTOs**: Data Transfer Objects for Network/JSON parsing (Moshi).
  - **Mappers**: Transform DTOs to Domain Models.
- **Dependency Injection**: **Dagger Hilt** is used for wiring dependencies across all layers.

## Build System

- **Gradle Version Catalogs**: Dependencies are centrally managed in `gradle/libs.versions.toml`.

## Project Structure

```text
e:/task_android/RealEstateApp
├── gradle
│   └── libs.versions.toml      // One simplified place for all dependencies
├── app/src/main/java/com/example/realestateapp
│   ├── RealEstateApplication.kt    // Hilt App
│   ├── MainActivity.kt             // Entry point
│   ├── di                          // Dependency Injection
│   │   ├── NetworkModule.kt
│   │   └── RepositoryModule.kt
│   ├── domain                      // [NEW] Domain Layer (Business Logic)
│   │   ├── model                   // Pure Domain Models
│   │   └── usecase                 // Interactors (GetListingsUseCase, etc.)
│   ├── data                        // Data Layer
│   │   ├── model                   // DTOs (ListingDto) & Mappers
│   │   ├── remote                  // Retrofit API
│   │   └── repository              // Repository Implementation
│   └── ui                          // Presentation Layer
│       ├── common                  // Shared UI (UiState, etc.)
│       ├── navigation              // NavGraph
│       ├── screens                 // Feature Screens (Listing, Details)
│       └── theme                   // Design System
```

## Libraries Setup

- **Kotlin**: 1.9.23
- **Jetpack Compose**: BOM 2023.08.00
- **Retrofit**: 2.9.0 (Networking)
- **Moshi**: 1.15.0 (JSON Parsing)
- **Coil**: 2.5.0 (Image Loading)
- **Navigation Compose**: 2.7.5
- **Dagger Hilt**: 2.48 (Dependency Injection)
- **Coroutines & Flow**: Asynchronous handling.
