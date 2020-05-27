package com.stefanusj.fruitstore.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.stefanusj.fruitstore.helper.setupSnackbar

/**
 * Base class for fragment with data binding
 */
abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {

    protected abstract val layoutId: Int

    protected abstract fun init()

    protected lateinit var binding: DB
    protected open val viewModel: BaseViewModel by viewModels()
    protected open val arguments: NavArgs by navArgs()

    protected open fun subscribeUi(): Unit = with(viewModel) {
        view?.setupSnackbar(viewLifecycleOwner, snackbarText)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        lifecycle.addObserver(viewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeUi()
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.apply {
            statusBarColor = Color.BLACK
            navigationBarColor = Color.BLACK
        }
    }
}