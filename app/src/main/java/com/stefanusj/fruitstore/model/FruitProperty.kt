package com.stefanusj.fruitstore.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fruit_property")
data class FruitProperty(
    @PrimaryKey @ColumnInfo(name = "fruit_id_property") val fruitId: Long,
    @ColumnInfo(name = "is_favorite", defaultValue = "0") val isFavorite: Boolean
)