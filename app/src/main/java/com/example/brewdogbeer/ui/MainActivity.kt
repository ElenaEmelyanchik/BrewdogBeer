package com.example.brewdogbeer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.brewdogbeer.data.BrewdogBeerItem
import com.example.brewdogbeer.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: BrewdogBeerListViewModelFactory

    lateinit var adapter: BrewdogBeerAdapter

    private val viewModel: BrewdogBeerListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(BrewdogBeerListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).component().inject(this)
        super.onCreate(savedInstanceState)
        initViews()
        initObservables()
    }

    private fun initObservables(){
        viewModel.getBrewdogBeerList().observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun initViews(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = BrewdogBeerAdapter{ brewdogBeerItem -> adapterOnClick(brewdogBeerItem) }
        binding.recycleView.adapter = adapter


    }

    private fun adapterOnClick(brewdogBeerItem: BrewdogBeerItem){
        BrewbogBeerDetailsActivity.start(this, brewdogBeerItem.id)
    }
}