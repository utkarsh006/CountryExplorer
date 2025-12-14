package com.example.myapplication.domain.model

data class Country(
    val name: String,
    val officialName: String,
    val capital: String?,
    val region: String,
    val subregion: String?,
    val population: Long,
    val area: Double?,
    val flagUrl: String?,
    val currency: String?,
    val language: String?,
    val countryCode: String
)
