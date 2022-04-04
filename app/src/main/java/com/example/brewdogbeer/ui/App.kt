package com.example.brewdogbeer.ui

import android.app.Application
import com.example.brewdogbeer.di.AppComponent
import com.example.brewdogbeer.di.AppModule
import com.example.brewdogbeer.di.DaggerAppComponent
import com.example.brewdogbeer.di.NetworkModule

class App: Application() {
    private val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .build()
    }

    fun component() = component
}