package com.example.myapplication.di

import com.example.myapplication.utils.Constants.BASE_URL
import com.example.myapplication.data.repository.CountryRepositoryImpl
import com.example.myapplication.domain.repository.CountryRepository
import com.example.myapplication.data.ApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class AppModule {

    @Binds
    abstract fun bindCountryRepository(
        countryRepositoryImpl: CountryRepositoryImpl
    ): CountryRepository

    companion object {
        @Provides
        @Singleton
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }
}