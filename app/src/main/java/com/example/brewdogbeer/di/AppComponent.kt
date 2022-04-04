package com.example.brewdogbeer.di

import com.example.brewdogbeer.api.Repository
import com.example.brewdogbeer.ui.BrewbogBeerDetailsActivity
import com.example.brewdogbeer.ui.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, AppModule::class])
interface AppComponent {

    fun repository(): Repository
    fun inject(activity: MainActivity)
    fun inject(activity: BrewbogBeerDetailsActivity)
}