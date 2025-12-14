package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Country
import kotlinx.coroutines.flow.Flow


interface CountryRepository {
    suspend fun getCountries(): Flow<Result<List<Country>>>
}
