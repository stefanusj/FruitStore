package com.stefanusj.fruitstore.ui.fruit

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import androidx.navigation.findNavController
import com.stefanusj.fruitstore.model.FruitWithProperty
import com.stefanusj.fruitstore.ui.BaseViewModel

class FruitViewModel(application: Application) : BaseViewModel(application) {

    private var _response = repository.fruits().distinctUntilChanged()
    val fruits = MediatorLiveData<List<FruitWithProperty>>()

    private val _isFavorite = MutableLiveData<Boolean>(false)
    val isFavorite: LiveData<Boolean> = _isFavorite

    val cart = repository.carts()
    val canCheckOut = cart.map { it.isNotEmpty() }

    val query = MutableLiveData<String>()

    init {
        fruits.addSource(_response) { fruits.value = it }
        fruits.addSource(query) { query ->
            fruits.value = _response.value?.filter {
                it.fruit.name.contains(query, true)
            }
        }
        fruits.addSource(isFavorite) { isFavorite ->
            fruits.value = _response.value?.filter {
                it.property?.isFavorite == isFavorite
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun fetchFruits() {
        launchDataLoad {
            repository.fetchFruits()
        }
    }

    // ----- Click Listener ----- //
    fun onFavoriteClicked(view: View) {
        launchDataLoad {
            _isFavorite.postValue(!_isFavorite.value!!)
        }
    }

    fun onCheckOutClicked(view: View) {
        FruitFragmentDirections.toCart().also(view.findNavController()::navigate)
    }
}