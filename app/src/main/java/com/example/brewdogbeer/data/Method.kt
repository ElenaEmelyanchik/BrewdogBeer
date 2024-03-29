package com.example.brewdogbeer.data

import com.google.gson.annotations.SerializedName

data class Method(
    @SerializedName("mash_temp")
    val mashTemp: List<MashTemp>,
    val fermentation: Fermentation,
    val twist: String?,
)
