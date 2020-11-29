package com.brewdog.beer.challenge.data.model

import com.google.gson.annotations.SerializedName

data class Beer(
    val id: String,
    val name: String,
    @SerializedName("image_url") val imageUrl: String,
    val avp: Double,
    val description: String,
    val ingredients: Ingredients,
    val method: Method
)