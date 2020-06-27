package com.xingyun.android.common.ext

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.replace() {

}

fun Fragment.showMessage(message: String) {
    context?.let { Toast.makeText(it.applicationContext, message, Toast.LENGTH_SHORT).show() }
}