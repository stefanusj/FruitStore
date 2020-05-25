package com.stefanusj.fruitstore.ui.fruit

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.stefanusj.fruitstore.R
import com.stefanusj.fruitstore.adapter.FruitAdapter
import com.stefanusj.fruitstore.databinding.FruitFragmentBinding
import com.stefanusj.fruitstore.helper.setupSnackbar
import com.stefanusj.fruitstore.ui.BaseFragment

class FruitFragment : BaseFragment<FruitFragmentBinding>() {

    override val layoutId: Int = R.layout.fruit_fragment
    override val viewModel: FruitViewModel by viewModels()

    override fun init() {
        binding.viewModel = viewModel
    }

    override fun subscribeUi(): Unit = with(viewModel) {
        view?.setupSnackbar(viewLifecycleOwner, snackbarText)
        fruits.observe(viewLifecycleOwner) {
            (binding.rvFruit.adapter as FruitAdapter).submitList(it)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) = with(binding) {
        super.onActivityCreated(savedInstanceState)

        val adapter = FruitAdapter()
        rvFruit.adapter = adapter
    }
}