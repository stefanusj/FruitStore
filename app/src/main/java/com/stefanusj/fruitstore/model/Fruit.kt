package com.stefanusj.fruitstore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fruit")
data class Fruit(
    @PrimaryKey val id: Long,
    val image: Image,
    val name: String,
    val unit: String,
    val price: Long
)