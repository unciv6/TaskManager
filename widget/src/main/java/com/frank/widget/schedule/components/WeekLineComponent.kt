package com.frank.widget.schedule.components

import android.graphics.*
import com.frank.widget.ScheduleConfig
import com.frank.widget.base.IScheduleComponent
import com.frank.widget.base.IScheduleModel
import com.frank.widget.schedule.ScheduleWidget
import com.frank.widget.schedule.clockWidth
import com.frank.widget.schedule.dateLineHeight
import com.frank.widget.tools.*
import kotlin.math.abs
import kotlin.math.roundToInt

class WeekLineComponent : IScheduleComponent<WeekLineModel> {
    override val model: WeekLineModel = WeekLineModel
    override val originRect: RectF = RectF(clockWidth, 0f, screenWidth.toFloat(), dateLineHeight)
    override val drawingRect: RectF = RectF(clockWidth, 0f, screenWidth.toFloat(), dateLineHeight)
    private val shadowRect: RectF = RectF(
        0f,
        dateLineHeight,
        screenWidth.toFloat(),
        dateLineHeight + 4f.dp
    )
    private var parentWidth = screenWidth
    private val dayWidth = (screenWidth - clockWidth) / 7
    private var scrollX = 0f
    private var parentScrollX = 0f
    private var radius = 13f.dp

    private val shadowShader by lazy {
        LinearGradient(
            shadowRect.left,
            shadowRect.top,
            shadowRect.left,
            shadowRect.bottom,
            ScheduleConfig.colorTransparent2,
            ScheduleConfig.colorTransparent1,
            Shader.TileMode.CLAMP
        )
    }

    override fun onDraw(canvas: Canvas, paint: Paint) {
        if (ScheduleWidget.isThreeDay) return
        parentWidth = canvas.width
        canvas.save()
        canvas.clipRect(shadowRect)
        paint.alpha = 255
        paint.shader = shadowShader
        canvas.drawRect(shadowRect, paint)
        paint.shader = null
        canvas.restore()
    }

    private val weekWidth = 7 * dayWidth
    override fun updateDrawingRect(anchorPoint: Point) {
        parentScrollX = -anchorPoint.x.toFloat()
        val oldScrollX = scrollX.roundToInt() / weekWidth.roundToInt() * weekWidth
        if (abs(-anchorPoint.x - oldScrollX) > weekWidth) {
            val dest = -anchorPoint.x
            scrollX = dest / weekWidth.roundToInt() * weekWidth
        }
    }
}

object WeekLineModel : IScheduleModel {
    override var beginTime: Long = 0
    override var endTime: Long = 0
}