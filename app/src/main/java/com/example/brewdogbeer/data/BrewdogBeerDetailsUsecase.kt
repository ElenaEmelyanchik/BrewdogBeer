package com.example.brewdogbeer.data

import com.example.brewdogbeer.api.Repository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class BrewdogBeerDetailsUsecase @Inject constructor(val repository: Repository) {

    fun execute(id: Int) : Single<BrewdogBeerItem> = repository.getBrewdogBeerDetails(id).map { item-> item.first()}
}