package com.example.myapplication.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.intent.MainIntent
import com.example.myapplication.presentation.adapter.CountriesAdapter
import com.example.myapplication.presentation.model.CountriesUiState
import com.example.myapplication.utils.ViewExtensions.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: MainViewModel

    private lateinit var countriesAdapter: CountriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setupRecyclerView()
        observeViewModel()
        loadInitialData()
    }

    private fun loadInitialData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.mainIntent.send(MainIntent.GetNews)
        }
    }

    private fun setupRecyclerView() {
        countriesAdapter = CountriesAdapter()
        
        binding.rvCountries.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = countriesAdapter
            setHasFixedSize(true)
        }

        countriesAdapter.setOnItemClickListener { country ->
            showToast(requireContext(), country.title, false)
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                handleUiState(state)
            }
        }
    }

    private fun handleUiState(state: CountriesUiState) {
        when (state) {
            is CountriesUiState.Loading -> {
                // show some loader
            }
            is CountriesUiState.Success -> {
                countriesAdapter.submitList(state.countries)
            }
            is CountriesUiState.Error -> {
                // show some error ui
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
