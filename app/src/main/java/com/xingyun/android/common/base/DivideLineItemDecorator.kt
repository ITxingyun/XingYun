package com.xingyun.android.common.base

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.xingyun.android.R

class DivideLineItemDecorator(private val resources: Resources) : RecyclerView.ItemDecoration() {
    private val bottomMargin = resources.getDimensionPixelOffset(R.dimen.recycler_view_divide_line_height)
    private val paint = Paint()

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.set(0, 0, 0, bottomMargin)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        paint.color = resources.getColor(R.color.coolgray_100, resources.newTheme())
        val itemCount = parent.adapter?.itemCount ?: 0
        for (index in 0 until itemCount) {
            val child = parent.getChildAt(index)
            child?.let {
                val left = it.left.toFloat()
                val top = it.top.toFloat()
                val right = it.right.toFloat()
                val bottom = it.bottom.toFloat()
                c.drawRect(left, bottom, right, bottom + bottomMargin, paint)
            }
        }
    }
}