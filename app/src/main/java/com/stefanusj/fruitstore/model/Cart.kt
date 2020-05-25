package com.stefanusj.fruitstore.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey @ColumnInfo(name = "fruit_id_cart") val fruitId: Long,
    val quantity: Int
)