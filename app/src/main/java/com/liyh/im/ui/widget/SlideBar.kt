package com.liyh.im.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.liyh.im.R
import org.jetbrains.anko.sp

/**
 * @author  Yahri Lee
 * @date  2018 年 07 月 17 日
 * @time  09 时 49 分
 * @descrip :
 */
class SlideBar(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {

    companion object {
        private val SECTIONS = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
                "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
    }

    var paint: Paint = Paint()
    var sectionHeight = 0f
    var textBaseline = 0f
    var onScetionChangeListener: OnScetionChangeListener? = null

    init {
        paint.apply {
            color = resources.getColor(R.color.qq_section_text_color)
            textSize = sp(12).toFloat()
            textAlign = Paint.Align.CENTER
            isAntiAlias = true
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        sectionHeight = h * 1.0f / SECTIONS.size
        val fontMetrics = paint.fontMetrics
        //计算绘制文本告诉
        val textHeight = fontMetrics.descent - fontMetrics.ascent
        //计算基准线
        textBaseline = sectionHeight / 2 + (textHeight / 2 - fontMetrics.descent)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val x = width * 1.0f / 2
        var baseline = textBaseline
        SECTIONS.forEach {
            canvas.drawText(it, x, baseline, paint)
            baseline += sectionHeight
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                setBackgroundResource(R.drawable.bg_slide_bar)
                val index = getTouchIndex(event)
                val section = SECTIONS[index]
                println(section)
                onScetionChangeListener?.onScetionChange(section)
            }
            MotionEvent.ACTION_MOVE -> {
                val index = getTouchIndex(event)
                val section = SECTIONS[index]
                println(section)
                onScetionChangeListener?.onScetionChange(section)

            }
            MotionEvent.ACTION_UP -> {
                setBackgroundColor(Color.TRANSPARENT)
                onScetionChangeListener?.onSlideFinished()
            }
        }
        return true
    }

    /**
     * 获取触摸的索引
     */
    private fun getTouchIndex(event: MotionEvent): Int {
        var index = (event.y / sectionHeight).toInt()
        if (index < 0) {
            index = 0
        } else if (index >= SECTIONS.size) {
            index = SECTIONS.size - 1
        }
        return index
    }

    interface OnScetionChangeListener {
        fun onScetionChange(firstLetter: String)
        fun onSlideFinished()
    }
}