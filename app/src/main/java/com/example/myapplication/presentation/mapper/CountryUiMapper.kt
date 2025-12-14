package com.example.myapplication.presentation.mapper

import com.example.myapplication.domain.model.Country
import com.example.myapplication.presentation.model.CountryUiModel


object CountryUiMapper {
    
    fun mapToUiModel(country: Country): CountryUiModel {
        return CountryUiModel(
            id = country.countryCode,
            title = "${country.name} - ${country.region}",
            subtitle = buildSubtitle(country),
            description = buildDescription(country),
            imageUrl = country.flagUrl,
            details = buildDetails(country)
        )
    }
    
    private fun buildSubtitle(country: Country): String {
        return country.capital?.let { "Capital: $it" } ?: "No capital"
    }
    
    fun mapToUiModelList(countries: List<Country>): List<CountryUiModel> {
        return countries.map { mapToUiModel(it) }
    }
    
    private fun buildDescription(country: Country): String {
        val parts = mutableListOf<String>()
        
        parts.add("Population: ${formatPopulation(country.population)}")
        
        country.currency?.let { parts.add("Currency: $it") }
        country.language?.let { parts.add("Language: $it") }
        
        return parts.joinToString(" | ")
    }
    
    private fun buildDetails(country: Country): String {
        val parts = mutableListOf<String>()
        
        country.area?.let { parts.add("Area: ${formatArea(it)}") }
        country.subregion?.let { parts.add("Subregion: $it") }
        
        return if (parts.isNotEmpty()) {
            parts.joinToString(" | ")
        } else {
            "Additional details not available"
        }
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
