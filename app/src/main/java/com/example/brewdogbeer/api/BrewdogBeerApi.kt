package com.example.brewdogbeer.api

import com.example.brewdogbeer.data.BrewdogBeerItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BrewdogBeerApi {
    @GET("v2/beers")
    fun getBrewdogBeerPage(@Query("page") page: Int): Single<ArrayList<BrewdogBeerItem>>

    @GET("v2/beers/{id}")
    fun getBrewdogBeerDetails(@Path("id") id: Int): Single<ArrayList<BrewdogBeerItem>>
}