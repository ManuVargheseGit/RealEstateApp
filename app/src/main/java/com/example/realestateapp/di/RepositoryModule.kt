package com.example.realestateapp.di

import com.example.realestateapp.data.repository.RealEstateRepository
import com.example.realestateapp.data.repository.RealEstateRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRealEstateRepository(
        realEstateRepositoryImpl: RealEstateRepositoryImpl
    ): RealEstateRepository
}
