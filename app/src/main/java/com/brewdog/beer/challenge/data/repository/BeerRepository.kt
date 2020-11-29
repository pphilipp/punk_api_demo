package com.brewdog.beer.challenge.data.repository

import com.brewdog.beer.challenge.data.api.ApiHelper

class BeerRepository(
    private val apiHelper: ApiHelper
) {
    suspend fun getBeerList(amount: Int) = apiHelper.getBeerList(perPage = amount)

    suspend fun getBeerDetailsById(beerId: String) = apiHelper.getBeerDetailsById(beerId = beerId)

    suspend fun getRandomBeerList() = apiHelper.getRandomBeerList()
}