package com.xingyun.android.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle

internal inline fun <reified T : Activity> Activity.start(
        requestCode: Int = -1,
        noinline configuration: Intent.() -> Unit = {},
        options: Bundle? = null
) {
    val intent = Intent(this, T::class.java)
    configuration(intent)
    if (requestCode != -1) {
        startActivityForResult(intent, requestCode, options)
    } else {
        startActivity(intent, options)
    }
}