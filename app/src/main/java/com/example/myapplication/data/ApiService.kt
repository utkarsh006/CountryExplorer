package com.example.myapplication.data

import retrofit2.http.GET

interface ApiService {
    @GET("independent?status=true")
    suspend fun getCountries(): List<CountryModel>
}