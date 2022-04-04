package com.example.brewdogbeer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.brewdogbeer.data.BrewdogBeerItem
import com.example.brewdogbeer.data.BrewdogBeerListUsecase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class BrewdogBeerListViewModel(val getListUsecase: BrewdogBeerListUsecase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val brewdogBeerList = MutableLiveData<ArrayList<BrewdogBeerItem>>()
    private val state = MutableLiveData<State>()

    init {
        getList()
    }

    private fun getList(){
        compositeDisposable.add(getListUsecase.execute().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    brewdogBeerList.value = it
                    state.value = State.Success
                },
                {
                    state.value = State.Error(it)
                }
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    fun getBrewdogBeerList() : LiveData<ArrayList<BrewdogBeerItem>> = brewdogBeerList

    sealed class State {
        object Success : State()
        class Error(val error: Throwable) : State()
    }


}