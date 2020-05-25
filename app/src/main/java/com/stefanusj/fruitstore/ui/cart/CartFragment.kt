package com.stefanusj.fruitstore.ui.cart

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.stefanusj.fruitstore.R
import com.stefanusj.fruitstore.adapter.CartAdapter
import com.stefanusj.fruitstore.databinding.CartFragmentBinding
import com.stefanusj.fruitstore.helper.setupSnackbar
import com.stefanusj.fruitstore.ui.BaseFragment

class CartFragment : BaseFragment<CartFragmentBinding>() {

    override val layoutId: Int = R.layout.cart_fragment
    override val viewModel: CartViewModel by viewModels()

    override fun init() {
        binding.viewModel = viewModel
    }

    override fun subscribeUi(): Unit = with(viewModel) {
        view?.setupSnackbar(viewLifecycleOwner, snackbarText)
        cart.observe(viewLifecycleOwner) {
            (binding.rvCart.adapter as CartAdapter).submitList(it)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) = with(binding) {
        super.onActivityCreated(savedInstanceState)

        val adapter = CartAdapter()
        rvCart.adapter = adapter
    }
}