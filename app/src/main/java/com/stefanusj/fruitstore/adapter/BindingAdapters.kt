package com.stefanusj.fruitstore.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.google.android.material.button.MaterialButton
import com.stefanusj.fruitstore.R
import com.stefanusj.fruitstore.helper.gone
import com.stefanusj.fruitstore.helper.toColorDrawable
import com.stefanusj.fruitstore.helper.visible
import java.text.NumberFormat
import java.util.*

@BindingAdapter("imageFromUrl")
fun imageFromUrl(imageView: ImageView, image: String?) {

    imageView.load(image) {

        val default = R.color.brown_primary.toColorDrawable(imageView.context)

        error(default)
        placeholder(default)
        fallback(default)
    }
}

@BindingAdapter("isFavorite")
fun isFavorite(button: MaterialButton, isFavorite: Boolean) {
    button.setIconResource(
        if (isFavorite) R.drawable.ic_favorite
        else R.drawable.ic_favorite_border
    )
}

@BindingAdapter("isVisible")
fun isVisible(view: View, isVisible: Boolean) {
    if (isVisible) {
        view.visible()
    } else {
        view.gone()
    }
}

@BindingAdapter("price", "unit", requireAll = false)
fun setUnitPrice(textView: TextView, price: Long?, unit: String?) {

    val locale = Locale("in", "ID")
    NumberFormat.getCurrencyInstance(locale).also {
        val priceRupiah = it.format(price?.toDouble() ?: 0)

        if (unit.isNullOrEmpty()) {
            textView.text = priceRupiah
        } else {
            textView.text = textView.context.getString(
                R.string.fruit_price_with_quantity,
                priceRupiah,
                unit
            )
        }
    }
}