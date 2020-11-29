package com.brewdog.beer.challenge.data.api

import com.brewdog.beer.challenge.data.model.Beer

class ApiHelper(
    private val apiService: PunkService
) {

    suspend fun getBeerList(page: Int? = 1, perPage: Int? = 1) =
        apiService.listBeer(page, perPage)

    suspend fun getRandomBeerList() =
        apiService.getRandomBeerList()

    suspend fun getBeerDetailsById(beerId: String) =
        apiService.getBeerDetailsById(beerId)
}
