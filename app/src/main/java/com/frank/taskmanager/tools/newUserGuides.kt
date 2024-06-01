package com.frank.taskmanager.tools

import com.frank.widget.base.DailyTaskModel
import com.frank.widget.tools.*
import java.util.Calendar

val newUserTasks = listOf(
    DailyTaskModel(
        beginTime = nowMillis.adjustTimestamp(
            hourMillis, true
        ) - 2 * hourMillis, title = "点击空白区域创建日程", duration = hourMillis
    ),
    DailyTaskModel(
        beginTime = nowMillis.adjustTimestamp(hourMillis, true).calendar.apply {
            add(Calendar.DAY_OF_MONTH, 1)
        }.timeInMillis + 1 * hourMillis, title = "长按可滑动", duration = 2 * hourMillis
    ),
)