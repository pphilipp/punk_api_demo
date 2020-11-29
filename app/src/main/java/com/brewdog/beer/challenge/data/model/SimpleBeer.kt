package com.brewdog.beer.challenge.data.model

import com.google.gson.annotations.SerializedName

data class SimpleBeer(
    val id: String,
    val name: String,
    @SerializedName("image_url") val imageUrl: String,
    val avp: Double,
)