package com.xingyun.android.common.adapter

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.recyclerview.widget.RecyclerView

class DivideLineItemDecorator(
        @Dimension private val divideHeight: Int,
        @ColorInt private val divideColor: Int
) : RecyclerView.ItemDecoration() {
    private val paint = Paint().apply { color = divideColor }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.set(0, 0, 0, divideHeight)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val itemCount = parent.adapter?.itemCount ?: 0
        for (index in 0 until itemCount) {
            val child = parent.getChildAt(index)
            child?.let {
                val left = it.left.toFloat()
                val right = it.right.toFloat()
                val bottom = it.bottom.toFloat()
                c.drawRect(left, bottom, right, bottom + divideHeight, paint)
            }
        }
    }
}