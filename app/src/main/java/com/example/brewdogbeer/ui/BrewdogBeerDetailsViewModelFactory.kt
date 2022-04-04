package com.example.brewdogbeer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.brewdogbeer.api.Repository
import com.example.brewdogbeer.data.BrewdogBeerDetailsUsecase
import javax.inject.Inject

class BrewdogBeerDetailsViewModelFactory @Inject constructor(val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BrewdogBeerDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BrewdogBeerDetailsViewModel(
                BrewdogBeerDetailsUsecase(repository)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}