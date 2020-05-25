package com.stefanusj.fruitstore.ui.cart

import android.app.Application
import android.view.View
import androidx.lifecycle.map
import androidx.navigation.findNavController
import com.stefanusj.fruitstore.R
import com.stefanusj.fruitstore.ui.BaseViewModel
import kotlinx.coroutines.delay

class CartViewModel(application: Application) : BaseViewModel(application) {

    val cart = repository.carts()

    val totalPrice = cart.map {
        it.fold(0L) { acc, fruitWithCart ->
            acc + fruitWithCart.fruit.price * fruitWithCart.cart.quantity
        }
    }

    // ----- Click Listener ----- //
    fun onOrderClicked(view: View) {
        launchDataLoad {
            delay(3_000)
            repository.resetCarts()
            postMessage(R.string.cart_success)
            view.findNavController().navigateUp()
        }
    }
}
