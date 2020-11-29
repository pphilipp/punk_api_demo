package com.brewdog.beer.challenge.data.api

import com.brewdog.beer.challenge.data.model.Beer
import com.brewdog.beer.challenge.data.model.SimpleBeer
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PunkService {

    @GET("beers")
    suspend fun listBeer(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?
    ): List<SimpleBeer>

    @GET("beers/random")
    suspend fun getRandomBeerList(): List<SimpleBeer>

    @GET("beers/{id}")
    suspend fun getBeerDetailsById(
        @Path("id") id: String,
    ): List<Beer>
}
