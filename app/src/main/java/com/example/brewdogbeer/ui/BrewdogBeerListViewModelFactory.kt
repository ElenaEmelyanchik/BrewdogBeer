package com.example.brewdogbeer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.brewdogbeer.api.Repository
import com.example.brewdogbeer.data.BrewdogBeerListUsecase
import javax.inject.Inject

class BrewdogBeerListViewModelFactory @Inject constructor(val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BrewdogBeerListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BrewdogBeerListViewModel(
                BrewdogBeerListUsecase(repository)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}