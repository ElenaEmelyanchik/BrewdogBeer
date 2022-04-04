package com.example.brewdogbeer.data

import com.example.brewdogbeer.api.Repository
import javax.inject.Inject

class BrewdogBeerListUsecase @Inject constructor(val repository: Repository) {

    fun execute()= repository.getBrewdogBeerList(1)
}