package com.stefanusj.fruitstore.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stefanusj.fruitstore.helper.TypeConverter
import com.stefanusj.fruitstore.model.Cart
import com.stefanusj.fruitstore.model.Fruit
import com.stefanusj.fruitstore.model.FruitProperty

/**
 * The Room database for this app
 */
@Database(
    entities = [Fruit::class, FruitProperty::class, Cart::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun localService(): LocalService

}

