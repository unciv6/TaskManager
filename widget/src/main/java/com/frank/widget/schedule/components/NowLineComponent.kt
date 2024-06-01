package com.frank.widget.schedule.components

import android.graphics.*
import com.frank.widget.base.IScheduleComponent
import com.frank.widget.base.IScheduleModel
import com.frank.widget.schedule.clockWidth
import com.frank.widget.schedule.dateLineHeight
import com.frank.widget.schedule.originRect
import com.frank.widget.schedule.refreshRect
import com.frank.widget.tools.dp
import com.frank.widget.tools.nowMillis

class NowLineComponent : IScheduleComponent<NowLineModel> {
    override val model: NowLineModel = NowLineModel
    override val originRect: RectF = originRect()
    override val drawingRect: RectF = originRect()

    override fun onDraw(canvas: Canvas, paint: Paint) {
        if (drawingRect.centerY() - 4f.dp < dateLineHeight) return
        canvas.save()
        canvas.clipRect(
            clockWidth,
            drawingRect.top - 10f.dp,
            drawingRect.right,
            drawingRect.bottom + 10f.dp
        )
        paint.color = Color.RED
        paint.strokeWidth = 1f.dp
        canvas.drawLine(
            drawingRect.left + 8f.dp,
            drawingRect.centerY(),
            drawingRect.right - 2f.dp,
            drawingRect.centerY(),
            paint
        )
        canvas.drawCircle(drawingRect.left + 4f.dp, drawingRect.centerY(), 3f.dp, paint)
        canvas.restore()
    }

    override fun updateDrawingRect(anchorPoint: Point) {
        refreshRect()
        drawingRect.left = originRect.left + anchorPoint.x
        drawingRect.right = originRect.right + anchorPoint.x
        drawingRect.top = originRect.top + anchorPoint.y
        drawingRect.bottom = originRect.bottom + anchorPoint.y
    }
}

object NowLineModel : IScheduleModel {
    override val beginTime: Long
        get() = nowMillis
    override val endTime: Long
        get() = nowMillis
}