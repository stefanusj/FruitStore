package com.stefanusj.fruitstore.helper

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar

/**
 * Triggers a snackbar message when the value contained by snackbarTaskMessageLiveEvent is modified.
 */
fun View.setupSnackbar(
    lifecycleOwner: LifecycleOwner,
    snackbarEvent: LiveData<Event<Int>>,
    timeLength: Int = Snackbar.LENGTH_SHORT
) {
    snackbarEvent.observeEvent(lifecycleOwner) {
        Snackbar.make(this, it, timeLength).show()
    }
}

/**
 * Set view visibility as visible
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * Set view visibility as gone
 */
fun View.gone() {
    visibility = View.GONE
}