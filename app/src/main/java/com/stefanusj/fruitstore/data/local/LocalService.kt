package com.stefanusj.fruitstore.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.stefanusj.fruitstore.model.*

@Dao
interface LocalService {

    @Transaction
    @Query("SELECT * FROM fruit LEFT JOIN fruit_property ON fruit.id=fruit_id_property LEFT JOIN cart ON fruit.id=fruit_id_cart WHERE id=:id")
    fun getFruit(id: Long): LiveData<FruitWithPropertyWithCart>

    @Transaction
    @Query("SELECT * FROM fruit LEFT JOIN fruit_property ON fruit.id=fruit_id_property")
    fun getFruits(): LiveData<List<FruitWithProperty>>

    @Transaction
    @Query("SELECT * FROM fruit JOIN cart ON fruit.id=fruit_id_cart")
    fun getCarts(): LiveData<List<FruitWithCart>>

    @Query("INSERT OR IGNORE INTO fruit_property (fruit_id_property) VALUES(:id)")
    fun initializeFruitProperty(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeFruits(fruits: List<Fruit>)

    @Query("UPDATE fruit_property SET is_favorite= NOT is_favorite WHERE fruit_id_property=:id")
    fun swapFavorite(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeCart(cart: Cart)

    @Query("DELETE FROM cart WHERE fruit_id_cart=:id")
    fun destroyCart(id: Long)

    @Query("DELETE FROM cart")
    fun resetCarts()

    @Query("DELETE FROM fruit")
    fun deleteAllFruits()

    @Transaction
    fun refreshFruits(fruits: List<Fruit>) {
        deleteAllFruits()
        storeFruits(fruits)
    }

}