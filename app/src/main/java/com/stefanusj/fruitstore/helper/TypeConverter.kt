package com.stefanusj.fruitstore.helper

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.stefanusj.fruitstore.model.Image

class TypeConverter {
    @TypeConverter
    fun imageToJson(images: Image?): String? {
        return images?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun jsonToImage(json: String?): Image? {
        return json?.let { Gson().fromJson(it, Image::class.java) }
    }
}