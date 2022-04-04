package com.example.brewdogbeer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.brewdogbeer.data.BrewdogBeerItem
import com.example.brewdogbeer.data.BrewdogBeerDetailsUsecase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class BrewdogBeerDetailsViewModel(private val getDetailsUsecase: BrewdogBeerDetailsUsecase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val brewdogBeerDetails = MutableLiveData<BrewdogBeerItem>()
    private val state = MutableLiveData<State>()

    fun getDetails(id: Int){
        compositeDisposable.add(getDetailsUsecase.execute(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    brewdogBeerDetails.value = it
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

    fun getBrewdogBeerDetails() : LiveData<BrewdogBeerItem> = brewdogBeerDetails

    sealed class State {
        object Success : State()
        class Error(val error: Throwable) : State()
    }
}