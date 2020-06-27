package com.xingyun.android.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 要来处理replaceFragment导致的内存泄漏
 */
class AutoClearedValue<T>(val fragment: Fragment) : ReadWriteProperty<Fragment, T>, LifecycleObserver {

    private var _value: T? = null

    init {
        fragment.lifecycle.addObserver(this)
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return _value ?: throw IllegalStateException(
            "Trying to call an auto-cleared value outside of the view lifecycle."
        )
    }


    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        _value = value
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        _value = null
        fragment.lifecycle.removeObserver(this)
    }

}

fun <T : Any> Fragment.autoCleared() = AutoClearedValue<T>(this)