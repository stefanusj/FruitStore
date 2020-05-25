package com.stefanusj.fruitstore.model

import androidx.room.Embedded

data class FruitWithProperty(
    @Embedded val fruit: Fruit,
    @Embedded val property: FruitProperty?
)