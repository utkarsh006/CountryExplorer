package com.example.myapplication.presentation.mapper

import com.example.myapplication.domain.model.Country
import com.example.myapplication.presentation.model.CountryUiModel


object CountryUiMapper {
    
    fun mapToUiModel(country: Country): CountryUiModel {
        return CountryUiModel(
            id = country.countryCode,
            title = "${country.name} - ${country.region}",
            subtitle = "Capital: ${country.capital}",
            description = buildDescription(country),
            imageUrl = country.flagUrl,
            details = buildDetails(country)
        )
    }
    
    fun mapToUiModelList(countries: List<Country>): List<CountryUiModel> {
        return countries.map { mapToUiModel(it) }
    }
    
    private fun buildDescription(country: Country): String {
        return "Population: ${formatPopulation(country.population)} | Currency: ${country.currency} | Language: ${country.language}"
    }
    
    private fun buildDetails(country: Country): String {
        return "Area: ${formatArea(country.area)} | Subregion: ${country.subregion}"
    }
    
    private fun formatPopulation(population: Long): String {
        return when {
            population >= 1_000_000 -> "${(population / 1_000_000.0).format(1)}M"
            population >= 1_000 -> "${(population / 1_000.0).format(1)}K"
            else -> population.toString()
        }
    }
    
    private fun formatArea(area: Double): String {
        return "${area.format(0)} km²"
    }
    
    private fun Double.format(digits: Int) = "%.${digits}f".format(this)
}
