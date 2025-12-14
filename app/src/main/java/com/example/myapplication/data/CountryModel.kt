package com.example.myapplication.data

import com.google.gson.annotations.SerializedName

data class CountryModel(
    @SerializedName("name") var name: Name? = null,
    @SerializedName("capital") var capital: List<String>? = null,
    @SerializedName("region") var region: String? = null,
    @SerializedName("subregion") var subregion: String? = null,
    @SerializedName("population") var population: Long? = null,
    @SerializedName("area") var area: Double? = null,
    @SerializedName("flags") var flags: Flags? = null,
    @SerializedName("currencies") var currencies: Map<String, Currency>? = null,
    @SerializedName("languages") var languages: Map<String, String>? = null,
    @SerializedName("cca3") var cca3: String? = null
) {
    data class Name(
        @SerializedName("common") var common: String? = null,
        @SerializedName("official") var official: String? = null
    )

    data class Flags(
        @SerializedName("png") var png: String? = null,
        @SerializedName("svg") var svg: String? = null,
        @SerializedName("alt") var alt: String? = null
    )

    data class Currency(
        @SerializedName("name") var name: String? = null,
        @SerializedName("symbol") var symbol: String? = null
    )
}