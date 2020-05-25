package com.stefanusj.fruitstore.model.response

abstract class BaseResponse {
    abstract val status: Boolean
    abstract val data: Any
}