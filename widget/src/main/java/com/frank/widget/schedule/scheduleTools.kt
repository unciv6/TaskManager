package com.frank.widget.schedule

import android.graphics.Point
import android.graphics.RectF
import com.frank.widget.base.IScheduleComponent
import com.frank.widget.base.IScheduleModel
import com.frank.widget.tools.*
import java.util.*
import kotlin.math.roundToInt
import kotlin.math.roundToLong

val dayWidth: Float
    get() = if (ScheduleWidget.isThreeDay) ((screenWidth - clockWidth) / 3).roundToInt().toFloat() else (screenWidth - clockWidth).roundToInt().toFloat()
val clockWidth = 56f.dp
val clockHeight = 50f.dp
val dayHeight = 50f.dp * 24
val dateLineHeight = 60f.dp
val canvasPadding = 10.dp
val zeroClockY = dateLineHeight + canvasPadding



fun IScheduleComponent<*>.refreshRect() {
    // x轴： 与当天的间隔天数 * 一天的宽度
    // y轴： 当前分钟数 / 一天的分钟数 * 一天的高度
    val today = nowMillis.dDays
    val day = model.beginTime.dDays
    val left = clockWidth + (day - today) * dayWidth
    val right = left + dayWidth
    val zeroClock = beginOfDay(model.beginTime)
    val top = dateLineHeight + dayHeight * (model.beginTime - zeroClock.time.time) / (hourMillis * 24)
    val bottom = dateLineHeight + dayHeight * (model.endTime - zeroClock.time.time) / (hourMillis * 24)
    originRect.set(left, top, right, bottom)
}

fun IScheduleModel.originRect(): RectF {
    // x轴： 与当天的间隔天数 * 一天的宽度
    // y轴： 当前分钟数 / 一天的分钟数 * 一天的高度
    val today = nowMillis.dDays
    val day = beginTime.dDays
    val left = clockWidth + (day - today) * dayWidth
    val right = left + dayWidth
    val zeroClock = beginOfDay(beginTime)
    val top = dateLineHeight + dayHeight * (beginTime - zeroClock.time.time) / (hourMillis * 24)
    val bottom = dateLineHeight + dayHeight * (endTime - zeroClock.time.time) / (hourMillis * 24)
    return RectF(left, top, right, bottom)
}

fun IScheduleComponent<*>.originRect(): RectF = model.originRect()

fun Point.positionToTime(scrollX: Int = 0, scrollY: Int = 0): Long {
    val days = ((x - clockWidth + scrollX) / dayWidth).roundToInt()
    val millis = ((y - dateLineHeight + scrollY) * hourMillis / clockHeight).roundToLong()
    val calendar = beginOfDay().apply {
        add(Calendar.DAY_OF_YEAR, days)
    }
    return calendar.timeInMillis + millis
}

val Float.yToMillis: Long
    get() = (this * hourMillis / clockHeight).roundToLong()

val Float.xToDDays: Int
    get() = ((this - clockWidth) / dayWidth).roundToInt()
