package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.usecase.GetCountriesUseCase
import com.example.myapplication.intent.MainIntent
import com.example.myapplication.presentation.mapper.CountryUiMapper
import com.example.myapplication.presentation.model.CountriesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    val mainIntent: Channel<MainIntent> = Channel(Channel.Factory.UNLIMITED)

    private val _uiState = MutableStateFlow<CountriesUiState>(CountriesUiState.Loading)
    val uiState: StateFlow<CountriesUiState> = _uiState.asStateFlow()

    init {
        handleIntent()
        loadCountries()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            mainIntent.consumeAsFlow().collect { intent ->
                when (intent) {
                    is MainIntent.GetNews -> loadCountries()
                    is MainIntent.Refresh -> loadCountries()
                }
            }
        }
    }

    private fun loadCountries() {
        viewModelScope.launch {
            _uiState.value = CountriesUiState.Loading

            getCountriesUseCase().collect { result ->
                _uiState.value = when {
                    result.isSuccess -> {
                        val countries = result.getOrNull() ?: emptyList()
                        val uiModels = CountryUiMapper.mapToUiModelList(countries)
                        CountriesUiState.Success(uiModels)
                    }

                    result.isFailure -> {
                        val error = result.exceptionOrNull()
                        CountriesUiState.Error(
                            error?.message ?: "Unknown error occurred"
                        )
                    }

                    else -> CountriesUiState.Error("Unknown error occurred")
                }
            }
        }
    }
}