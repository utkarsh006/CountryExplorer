package com.example.myapplication.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.intent.MainIntent
import com.example.myapplication.presentation.adapter.CountriesAdapter
import com.example.myapplication.presentation.model.CountriesUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val mainViewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory)[MainViewModel::class.java]
    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        observeData()

        lifecycleScope.launch {
            mainViewModel.mainIntent.send(MainIntent.GetNews)
        }
    }

    private fun observeData() {
        val adapter = CountriesAdapter()
        binding.rvCountries.layoutManager = LinearLayoutManager(this)
        binding.rvCountries.adapter = adapter

        lifecycleScope.launch {
            mainViewModel.uiState.collect {
                when (it) {
                    is CountriesUiState.Loading -> {
                        // load some progress bar
                    }

                    is CountriesUiState.Success -> {
                        adapter.submitList(it.countries)
                    }

                    is CountriesUiState.Error -> {
                        // show exception
                    }
                }
            }
        }
    }
}