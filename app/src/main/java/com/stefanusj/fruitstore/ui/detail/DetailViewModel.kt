package com.stefanusj.fruitstore.ui.detail

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import androidx.navigation.findNavController
import com.stefanusj.fruitstore.R
import com.stefanusj.fruitstore.helper.getPlatformColor
import com.stefanusj.fruitstore.model.Cart
import com.stefanusj.fruitstore.ui.BaseViewModel

class DetailViewModel(application: Application) : BaseViewModel(application) {

    private val _id = MutableLiveData<Long>()

    val fruitWithPropertyWithCart = _id.switchMap { repository.fruit(it) }
    val isFavorite = fruitWithPropertyWithCart.map { it.property.isFavorite }

    val color = fruitWithPropertyWithCart.switchMap {
        liveData {
            val color = getPlatformColor(application, it.fruit.image.small, 0.7f)
            emit(color)
        }
    }

    private val _quantity = MediatorLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    fun setId(id: Long) {
        _id.postValue(id)
        _quantity.addSource(fruitWithPropertyWithCart) {
            _quantity.value = it.cart?.quantity ?: 1
        }
    }

    // ----- Click Listener ----- //
    fun onIncrementClicked(view: View) {
        _quantity.value = (_quantity.value ?: 0) + 1
    }

    fun onDecrementClicked(view: View) {
        _quantity.value?.let {
            if (it == 0) return
            else _quantity.value = (_quantity.value ?: 0) - 1
        }
    }

    fun onFavoriteClicked(view: View) {
        launchDataLoad {
            repository.swapFavorite(fruitWithPropertyWithCart.value!!.fruit)
            if ((isFavorite.value != null) and isFavorite.value!!) {
                postMessage(R.string.removed_from_favorite)
            } else {
                postMessage(R.string.added_to_favorite)
            }
        }
    }

    fun onAddToCartClicked(view: View) {
        launchDataLoad {
            val cart = Cart(fruitId = _id.value!!, quantity = quantity.value!!)
            if (cart.quantity == 0) {
                repository.destroyCart(cart)
                postMessage(R.string.removed_from_cart)
            } else {
                repository.storeCart(cart)
                postMessage(R.string.added_to_cart)
            }
            view.findNavController().navigateUp()
        }
    }
}