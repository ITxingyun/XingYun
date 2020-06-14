//package com.xogrp.planner.imagewatch
//
//import android.graphics.drawable.BitmapDrawable
//import android.util.Log
//import android.view.ViewTreeObserver
//import android.widget.ImageView
//import com.taobao.android.dexposed.XC_MethodHook
//
//class ImageHook : XC_MethodHook() {
//
//    override fun afterHookedMethod(param: MethodHookParam) {
//        val imageView = param.thisObject as ImageView
//        checkBitmap(imageView)
//    }
//
//    companion object {
//        @JvmStatic
//        fun checkBitmap(imageView: ImageView) {
//            val drawable = imageView.drawable
//            if (drawable is BitmapDrawable) {
//                val bitmap = drawable.bitmap
//                val width = imageView.width
//                val height = imageView.height
//                if (width > 0 && height > 0) {
//                    if (bitmap.width >= (width.shl(1)) && bitmap.height >= height.shl(1)) {
//                        warn(width, height, bitmap.width, bitmap.height, RuntimeException("Bitmap Size is too large"))
//                    }
//                } else {
//                    imageView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
//                        override fun onPreDraw(): Boolean {
//                            if (width > 0 && height > 0) {
//                                if (bitmap.width >= (width.shl(1)) && bitmap.height >= height.shl(1)) {
//                                    warn(width, height, bitmap.width, bitmap.height, RuntimeException("Bitmap Size is too large"))
//                                }
//                                imageView.viewTreeObserver.removeOnPreDrawListener(this)
//                            }
//                            return true
//                        }
//                    })
//                }
//
//            }
//
//        }
//
//
//        @JvmStatic
//        fun warn(bitmapWidth: Int, bitmapHeight: Int, viewWidth: Int, viewHeight: Int, t: Throwable) {
//            val stringBuilder = StringBuilder()
//            stringBuilder.run {
//                append("Bitmap size too large: ")
//                append("\n real size: ($bitmapWidth,$bitmapHeight)")
//                append("\n desired size: ($viewWidth,$viewHeight)")
//                append("\n call stack trace: \n + ${Log.getStackTraceString(t)} + \n")
//            }
//
//            Log.e("ImageHook-> ", stringBuilder.toString())
//
//        }
//    }
//}