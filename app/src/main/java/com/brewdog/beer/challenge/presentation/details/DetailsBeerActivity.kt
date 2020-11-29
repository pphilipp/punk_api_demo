package com.brewdog.beer.challenge.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.brewdog.beer.challenge.R
import com.brewdog.beer.challenge.data.api.ApiHelper
import com.brewdog.beer.challenge.data.api.RetrofitBuilder
import com.brewdog.beer.challenge.databinding.ActivityBeerDetailsBinding
import com.brewdog.beer.challenge.presentation.base.CoroutineContextProvider
import com.brewdog.beer.challenge.presentation.base.ViewModelFactory
import com.brewdog.beer.challenge.util.Status
import com.brewdog.beer.challenge.util.observeNonNull

class DetailsBeerActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailsViewModel
    private lateinit var binding: ActivityBeerDetailsBinding
    private lateinit var adapterHop: HopAdapter
    private lateinit var mashTempAdapter: MashTempAdapter
    private lateinit var maltAdapter: MaltAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_beer_details)
        setupViewModel()
        setupContent()
        setupIngredients()
        setupMashTempAdapter()
    }

    private fun setupIngredients() {
        setupHopAdapter()
        setupMaltAdapter()
    }

    private fun setupHopAdapter() {
        adapterHop = HopAdapter()
        binding.includeIngredientsHopsMalt.hopsRv.layoutManager =
            LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        binding.includeIngredientsHopsMalt.hopsRv.itemAnimator = DefaultItemAnimator()
        binding.includeIngredientsHopsMalt.hopsRv.adapter = adapterHop
    }

    private fun setupMaltAdapter() {
        maltAdapter = MaltAdapter()
        binding.includeIngredientsHopsMalt.maltRv.layoutManager =
            LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        binding.includeIngredientsHopsMalt.maltRv.itemAnimator = DefaultItemAnimator()
        binding.includeIngredientsHopsMalt.maltRv.adapter = maltAdapter
    }

    private fun setupMashTempAdapter() {
        mashTempAdapter = MashTempAdapter()
        binding.includeMethod.mashTempRv.layoutManager =
            LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        binding.includeMethod.mashTempRv.itemAnimator = DefaultItemAnimator()
        binding.includeMethod.mashTempRv.adapter = mashTempAdapter
    }

    private fun setupContent() {
        intent.extras?.getString(KEY_BEER_EXTRA)?.let { beerId ->
            viewModel.fetchBeerDetails(beerId).observeNonNull(this) { resource ->
                binding.apply {
                    when (resource.status) {
                        Status.SUCCESS -> {
                            resource.data?.let {
                                item = it[0]
                                adapterHop.submitList(it[0].ingredients.hops)
                                maltAdapter.submitList(it[0].ingredients.malt)
                                mashTempAdapter.submitList(it[0].method.mashTemp)
                            }
                            contentSv.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                        }
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                            contentSv.visibility = View.GONE
                        }
                        Status.ERROR -> {
                            contentSv.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            Toast.makeText(applicationContext, resource.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService), CoroutineContextProvider())
        ).get(DetailsViewModel::class.java)
    }

    companion object {
        const val KEY_BEER_EXTRA = "key_beer_extra"

        fun newInstance(context: Context, beerId: String): Intent =
            Intent(context, DetailsBeerActivity::class.java).putExtra(KEY_BEER_EXTRA, beerId)
    }
}