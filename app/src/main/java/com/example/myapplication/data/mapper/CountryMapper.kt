package com.example.myapplication.data.mapper

import com.example.myapplication.domain.model.Country
import com.example.myapplication.data.CountryModel


object CountryMapper {
    
    fun mapToDomain(apiModel: CountryModel): Country {
        return Country(
            name = apiModel.name?.common ?: "Unknown",
            officialName = apiModel.name?.official ?: "Unknown",
            capital = apiModel.capital?.firstOrNull() ?: "Unknown",
            region = apiModel.region ?: "Unknown",
            subregion = apiModel.subregion ?: "Unknown",
            population = apiModel.population ?: 0L,
            area = apiModel.area ?: 0.0,
            flagUrl = apiModel.flags?.png ?: "",
            currency = apiModel.currencies?.values?.firstOrNull()?.name ?: "Unknown",
            language = apiModel.languages?.values?.firstOrNull() ?: "Unknown",
            countryCode = apiModel.cca3 ?: "Unknown"
        )
    }
    
    fun mapToDomainList(apiModels: List<CountryModel>): List<Country> {
        return apiModels.map { mapToDomain(it) }
    }
}
