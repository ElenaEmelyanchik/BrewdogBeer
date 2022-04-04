package com.example.brewdogbeer.api

import com.example.brewdogbeer.data.BrewdogBeerItem
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun getBrewdogBeerList(page:Int): Single<ArrayList<BrewdogBeerItem>>
    fun getBrewdogBeerDetails(id:Int):Single<ArrayList<BrewdogBeerItem>>
}