package com.example.myapplication.presentation.model

/**
 * UI model for displaying country information
 * Presentation layer: Optimized for UI display
 */
data class CountryUiModel(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val imageUrl: String,
    val details: String
)

/**
 * UI State for countries screen
 */
sealed class CountriesUiState {
    object Loading : CountriesUiState()
    data class Success(val countries: List<CountryUiModel>) : CountriesUiState()
    data class Error(val message: String) : CountriesUiState()
}
