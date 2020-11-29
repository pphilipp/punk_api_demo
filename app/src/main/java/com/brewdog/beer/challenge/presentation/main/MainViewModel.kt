package com.brewdog.beer.challenge.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.brewdog.beer.challenge.data.model.Beer
import com.brewdog.beer.challenge.data.model.SimpleBeer
import com.brewdog.beer.challenge.data.repository.BeerRepository
import com.brewdog.beer.challenge.presentation.base.CoroutineContextProvider
import com.brewdog.beer.challenge.presentation.main.navigation.MainNavigation
import com.brewdog.beer.challenge.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class MainViewModel(
    private val beerRepository: BeerRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel(){

    private val navigationMutableLiveData = MutableLiveData<MainNavigation>()
    val navigationLiveData = navigationMutableLiveData

    fun fetchBeerList(amount: Int) = liveData(coroutineContextProvider.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = beerRepository.getBeerList(amount)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun fetchRandomBeerList() = liveData(coroutineContextProvider.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = beerRepository.getRandomBeerList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun onItemClick(beerItem: SimpleBeer) {
        navigationLiveData.value = MainNavigation.NavigateToDetails(beerItem.id)
    }

}