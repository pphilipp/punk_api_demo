package com.brewdog.beer.challenge.presentation.main.navigation

import com.brewdog.beer.challenge.data.model.Beer

sealed class MainNavigation {
    class NavigateToDetails(val beerId: String) : MainNavigation()
}