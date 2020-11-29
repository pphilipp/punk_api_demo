package com.brewdog.beer.challenge.util

import androidx.databinding.ViewDataBinding

/**
 * @param key is variable id, like BR.viewModel
 */
fun <VDB : ViewDataBinding> VDB.setBindingViewModel(key: Int, viewModel: Any) =
    apply { setVariable(key, viewModel) }
