package com.example.myapplication.data.repository

import com.example.myapplication.data.mapper.CountryMapper
import com.example.myapplication.domain.model.Country
import com.example.myapplication.domain.repository.CountryRepository
import com.example.myapplication.data.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CountryRepository {

    override suspend fun getCountries(): Flow<Result<List<Country>>> = flow {
        try {
            val apiResponse = apiService.getCountries()

            val countries = CountryMapper.mapToDomainList(apiResponse)
                .take(30) // Limit to 30 for performance

            emit(Result.success(countries))

        } catch (exception: Exception) {
            emit(Result.failure(exception))
        }
    }
}
