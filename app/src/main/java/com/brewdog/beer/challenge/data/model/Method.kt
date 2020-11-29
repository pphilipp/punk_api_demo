package com.brewdog.beer.challenge.data.model

import com.google.gson.annotations.SerializedName

data class Method(
    @SerializedName("mash_temp")
    val mashTemp: List<MethodMashTemp>,
    val fermentation : Fermentation,
    val twist : String
)