package com.stefanusj.fruitstore.ui

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.stefanusj.fruitstore.R
import com.stefanusj.fruitstore.data.AppRepository
import com.stefanusj.fruitstore.helper.Event
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Base class for view model with data binding
 */
abstract class BaseViewModel(application: Application) :
    AndroidViewModel(application),
    LifecycleObserver,
    KoinComponent {

    protected val repository: AppRepository by inject()

    /**
     * Show a loading spinner if [isLoading] true
     */
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    /**
     * Variable container for event notification
     */
    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText

    /**
     * Post message to Observer UI using LiveData object
     * from given [resId]
     */
    fun postMessage(@StringRes resId: Int) = _snackbarText.postValue(Event(resId))

    /**
     * Launch a data load using coroutine [viewModelScope]
     *
     * Wrapped inside try catch to make sure apps aren't crash
     * while calling data from API
     */
    protected fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _isLoading.value = true
                block()
            } catch (e: Exception) {
                e.printStackTrace()
                postMessage(R.string.failed_connect_server)
            } finally {
                _isLoading.value = false
            }
        }
    }
}