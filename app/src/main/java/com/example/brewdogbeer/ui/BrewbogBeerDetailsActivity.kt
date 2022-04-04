package com.example.brewdogbeer.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.brewdogbeer.databinding.ActivityDetailsBinding
import javax.inject.Inject

class BrewbogBeerDetailsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    @Inject
    lateinit var viewModelFactory: BrewdogBeerDetailsViewModelFactory
    private val viewModel: BrewdogBeerDetailsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(BrewdogBeerDetailsViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).component().inject(this)
        super.onCreate(savedInstanceState)
        viewModel.getDetails(intent?.extras?.getInt(BEER_ID)?:0)
        initViews()
        initObservables()
    }

    private fun initObservables(){
        viewModel.getBrewdogBeerDetails().observe(this, Observer {
            Glide.with(this).load(it.image_url).into(binding.image)
            binding.name.text = it.name
            binding.abv.text = it.abv.toString()
            binding.description.text = it.description
            binding.hops.text = it.ingredients?.hops?.joinToString(separator = ", ") { it.name }
            binding.malts.text = it.ingredients?.malt?.joinToString(separator = ", "){ it.name }
            binding.methods.text = it.method?.toString()
        })
    }

    private fun initViews(){
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object{
        const val BEER_ID = "BEER_ID"
        fun start(activity: AppCompatActivity, id: Int){
            activity.startActivity(Intent(activity, BrewbogBeerDetailsActivity::class.java).apply {
                putExtra(BEER_ID, id)
            })
        }
    }
}