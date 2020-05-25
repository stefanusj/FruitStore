package com.stefanusj.fruitstore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.stefanusj.fruitstore.databinding.AppActivityBinding

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<AppActivityBinding>(this, R.layout.app_activity)
    }
}