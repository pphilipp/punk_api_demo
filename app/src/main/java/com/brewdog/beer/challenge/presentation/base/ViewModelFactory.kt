package com.brewdog.beer.challenge.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brewdog.beer.challenge.data.api.ApiHelper
import com.brewdog.beer.challenge.data.repository.BeerRepository
import com.brewdog.beer.challenge.presentation.details.DetailsViewModel
import com.brewdog.beer.challenge.presentation.main.MainViewModel
import kotlin.coroutines.CoroutineContext

class ViewModelFactory(
    private val apiHelper: ApiHelper,
    private val coroutineContext: CoroutineContextProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(BeerRepository(apiHelper), coroutineContext) as T
        }
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(BeerRepository(apiHelper), coroutineContext) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}