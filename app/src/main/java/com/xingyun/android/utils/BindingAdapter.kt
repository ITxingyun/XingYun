package com.xingyun.android.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout
import com.xingyun.android.core.model.Banner
import com.xingyun.android.ui.adapter.BannerAdapter

@BindingAdapter("textInputError")
fun displayErrorMsg(textInputLayout: TextInputLayout, resId: Int) {
    if (resId != 0) {
        textInputLayout.error = textInputLayout.context.getString(resId)
    }
}


@BindingAdapter("app:adapter")
fun displayBannerPager(viewPager: ViewPager2, data: List<Banner>) {
    viewPager.adapter = BannerAdapter(data)
}

@BindingAdapter("app:itemImageUrl")
fun displayImage(imageView: ImageView, itemImageUrl: String) {
    Glide.with(imageView)
            .load(itemImageUrl)
            .into(imageView)
}
