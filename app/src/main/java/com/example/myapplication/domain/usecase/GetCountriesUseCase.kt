package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Country
import com.example.myapplication.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCountriesUseCase @Inject constructor(
    private val repository: CountryRepository
) {
    suspend operator fun invoke(): Flow<Result<List<Country>>> {
        return repository.getCountries()
    }
}
