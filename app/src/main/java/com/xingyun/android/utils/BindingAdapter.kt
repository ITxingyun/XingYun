package com.xingyun.android.utils

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("textInputError")
fun displayErrorMsg(textInputLayout: TextInputLayout, resId: Int) {
    if (resId != 0) {
        textInputLayout.error = textInputLayout.context.getString(resId)
    }
}

