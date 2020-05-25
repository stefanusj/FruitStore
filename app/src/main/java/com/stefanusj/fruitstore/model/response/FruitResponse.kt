package com.stefanusj.fruitstore.model.response

import com.stefanusj.fruitstore.model.Fruit

data class FruitResponse(
    override val status: Boolean,
    override val data: List<Fruit>
) : BaseResponse()