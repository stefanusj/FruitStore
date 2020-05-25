package com.stefanusj.fruitstore.ui.detail

import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.stefanusj.fruitstore.R
import com.stefanusj.fruitstore.databinding.DetailFragmentBinding
import com.stefanusj.fruitstore.helper.setupSnackbar
import com.stefanusj.fruitstore.helper.toColorStateList
import com.stefanusj.fruitstore.ui.BaseFragment

class DetailFragment : BaseFragment<DetailFragmentBinding>() {

    override val layoutId: Int = R.layout.detail_fragment
    override val viewModel: DetailViewModel by viewModels()
    override val arguments: DetailFragmentArgs by navArgs()

    override fun init() {
        binding.viewModel = viewModel
        binding.setBackListener { findNavController().navigateUp() }

        viewModel.setId(arguments.id)
    }

    override fun subscribeUi(): Unit = with(viewModel) {
        view?.setupSnackbar(viewLifecycleOwner, snackbarText)
        color.observe(viewLifecycleOwner) {
            activity?.window?.statusBarColor = it
            activity?.window?.navigationBarColor = it
            binding.content.backgroundTintList = it.toColorStateList()
        }
    }
}