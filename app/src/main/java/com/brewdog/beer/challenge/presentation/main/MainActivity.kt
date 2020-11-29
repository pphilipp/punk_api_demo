package com.brewdog.beer.challenge.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.brewdog.beer.challenge.R
import com.brewdog.beer.challenge.data.api.ApiHelper
import com.brewdog.beer.challenge.data.api.RetrofitBuilder
import com.brewdog.beer.challenge.databinding.ActivityMainBinding
import com.brewdog.beer.challenge.presentation.base.CoroutineContextProvider
import com.brewdog.beer.challenge.presentation.base.ViewModelFactory
import com.brewdog.beer.challenge.presentation.details.DetailsBeerActivity
import com.brewdog.beer.challenge.util.Status
import com.brewdog.beer.challenge.util.observeNonNull
import com.brewdog.beer.challenge.presentation.main.navigation.MainNavigation.NavigateToDetails as NavigateToDetails

const val DEFAULT_BEER_AMOUNT = 10

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var beerAdapter: BeerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        setupUI()
        setupAdapter()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService), CoroutineContextProvider())
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        viewModel.navigationLiveData.observe(this, {
            when (it) {
                is NavigateToDetails -> {
                    startActivity(DetailsBeerActivity.newInstance(this, it.beerId))
                }
            }
        })

        viewModel.fetchBeerList(DEFAULT_BEER_AMOUNT).observeNonNull(this) {
            binding.apply {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { data ->
                            beerAdapter.submitList(data)
                            progressBar.visibility = View.GONE
                            recyclerView.visibility = View.VISIBLE
                        }
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(applicationContext, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun setupAdapter() {
        beerAdapter = BeerAdapter(viewModel::onItemClick)
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.adapter = beerAdapter
    }

    companion object {
        fun newInstance(context: Context) = Intent(context, MainActivity::class.java)
    }
}