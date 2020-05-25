package com.stefanusj.fruitstore.model

import androidx.room.Embedded

data class FruitWithPropertyWithCart(
    @Embedded val fruit: Fruit,
    @Embedded val property: FruitProperty,
    @Embedded val cart: Cart?
)