package com.brewdog.beer.challenge.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.brewdog.beer.challenge.data.repository.BeerRepository
import com.brewdog.beer.challenge.presentation.base.CoroutineContextProvider
import com.brewdog.beer.challenge.util.Resource
import kotlinx.coroutines.Dispatchers

class DetailsViewModel(
    private val beerRepository: BeerRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {

    fun fetchBeerDetails(beerId: String) = liveData(coroutineContextProvider.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = beerRepository.getBeerDetailsById(beerId)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}