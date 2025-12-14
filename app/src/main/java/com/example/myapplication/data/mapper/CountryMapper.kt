package com.example.myapplication.data.mapper

import com.example.myapplication.domain.model.Country
import com.example.myapplication.data.CountryModel

object CountryMapper {
    
    fun mapToDomain(apiModel: CountryModel): Country? {
        val name = apiModel.name?.common
        val region = apiModel.region
        val countryCode = apiModel.cca3
        
        // Exclude countries with missing required data
        if (name.isNullOrBlank() || region.isNullOrBlank() || countryCode.isNullOrBlank()) {
            return null
        }
        
        return Country(
            name = name,
            officialName = apiModel.name?.official ?: name,
            capital = apiModel.capital?.firstOrNull(),
            region = region,
            subregion = apiModel.subregion,
            population = apiModel.population ?: 0L,
            area = apiModel.area,
            flagUrl = apiModel.flags?.png,
            currency = apiModel.currencies?.values?.firstOrNull()?.name,
            language = apiModel.languages?.values?.firstOrNull(),
            countryCode = countryCode
        )
    }
    
    fun mapToDomainList(apiModels: List<CountryModel>): List<Country> {
        return apiModels.mapNotNull { mapToDomain(it) }
    }
}
