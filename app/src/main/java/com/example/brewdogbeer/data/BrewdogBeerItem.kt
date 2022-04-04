package com.example.brewdogbeer.data

data class BrewdogBeerItem(
    val id: Int,
    val image_url: String?,
    val name: String?,
    val abv: Float?,
    val description: String?,
    val ingredients: Ingredients?,
    val method: Method?,
)
