package com.xingyun.android.utils

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class TestObserver(private val lifecycleOwner: LifecycleOwner): LifecycleObserver {

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.e("test", "${lifecycleOwner.javaClass.simpleName}: ON_CREATE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.e("test", "${lifecycleOwner.javaClass.simpleName}: ON_START")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Log.e("test", "${lifecycleOwner.javaClass.simpleName}: ON_RESUME")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onPause() {
        Log.e("test", "${lifecycleOwner.javaClass.simpleName}: ON_RESUME")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Log.e("test", "${lifecycleOwner.javaClass.simpleName}: ON_STOP")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.e("test", "${lifecycleOwner.javaClass.simpleName}: ON_DESTROY")
    }


}