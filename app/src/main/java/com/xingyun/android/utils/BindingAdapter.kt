package com.xingyun.android.utils

import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("textInputError")
fun displayErrorMsg(textInputLayout: TextInputLayout, resId: Int) {
    if (resId != 0) {
        textInputLayout.error = textInputLayout.context.getString(resId)
    }
}

@BindingAdapter("app:itemImageUrl")
fun displayImage(imageView: ImageView, itemImageUrl: String) {
    Glide.with(imageView)
            .load(itemImageUrl)
            .fitCenter()
            .into(imageView)
}


@BindingAdapter("app:isRefresh")
fun SwipeRefreshLayout.isRefresh(isRefresh: Boolean) {
    isRefreshing = isRefresh
}

@BindingAdapter("app:onRefresh")
fun SwipeRefreshLayout.onRefresh(action: () -> Unit) {
    setOnRefreshListener { action() }
}

@BindingAdapter("htmlText")
fun bindHtmlText(view: TextView, html:String){
    view.text = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
}