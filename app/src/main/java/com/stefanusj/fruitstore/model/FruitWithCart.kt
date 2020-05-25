package com.stefanusj.fruitstore.model

import androidx.room.Embedded

data class FruitWithCart(
    @Embedded val fruit: Fruit,
    @Embedded val cart: Cart
)