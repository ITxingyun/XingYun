package com.xingyun.android.utils

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class LifecycleHandler: Handler, LifecycleObserver {

    private lateinit var lifecycleOwner: LifecycleOwner

    constructor(lifecycleOwner: LifecycleOwner) : super() {
        addObserver(lifecycleOwner)
    }

    constructor(callback: Callback?, lifecycleOwner: LifecycleOwner) : super(callback) {
        addObserver(lifecycleOwner)
    }

    constructor(looper: Looper, lifecycleOwner: LifecycleOwner) : super(looper) {
        addObserver(lifecycleOwner)
    }

    constructor(looper: Looper, callback: Callback?, lifecycleOwner: LifecycleOwner) : super(looper, callback) {
        addObserver(lifecycleOwner)
    }


    private fun addObserver(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
        lifecycleOwner.lifecycle.addObserver(this)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        removeCallbacksAndMessages(null)
        lifecycleOwner.lifecycle.removeObserver(this)
    }

}