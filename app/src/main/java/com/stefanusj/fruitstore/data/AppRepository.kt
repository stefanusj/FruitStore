package com.stefanusj.fruitstore.data

import android.content.Context
import com.stefanusj.fruitstore.data.local.LocalService
import com.stefanusj.fruitstore.data.network.WebService
import com.stefanusj.fruitstore.model.Cart
import com.stefanusj.fruitstore.model.Fruit
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject


class AppRepository(val context: Context) : KoinComponent {

    private val localService: LocalService by inject()
    private val webService: WebService by inject()

    fun fruit(id: Long) = localService.getFruit(id)

    fun fruits() = localService.getFruits()

    fun carts() = localService.getCarts()

    suspend fun swapFavorite(fruit: Fruit) = withContext(IO) {
        localService.swapFavorite(fruit.id)
    }

    suspend fun storeCart(cart: Cart) = withContext(IO) {
        localService.storeCart(cart)
    }

    suspend fun destroyCart(cart: Cart) = withContext(IO) {
        localService.destroyCart(cart.fruitId)
    }

    suspend fun resetCarts() = withContext(IO) {
        localService.resetCarts()
    }

    //------ Network Data -----//
    suspend fun fetchFruits() = withContext(IO) {
        val response = webService.getFruits()
        val fruits = response.data
        localService.refreshFruits(fruits)
        fruits.forEach { localService.initializeFruitProperty(it.id) }
    }
}