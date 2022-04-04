package com.example.brewdogbeer.api

import com.example.brewdogbeer.data.BrewdogBeerItem
import io.reactivex.rxjava3.core.Single

class ApiRestRepository(val api: BrewdogBeerApi): Repository {
    override fun getBrewdogBeerList(page: Int): Single<ArrayList<BrewdogBeerItem>> {
        return api.getBrewdogBeerPage(page)
    }

    override fun getBrewdogBeerDetails(id: Int): Single<ArrayList<BrewdogBeerItem>> {
        return api.getBrewdogBeerDetails(id)
    }
}