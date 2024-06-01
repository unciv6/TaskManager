package com.frank.widget.flow.list

import com.frank.widget.base.IScheduleModel
import com.frank.widget.tools.calendar
import com.frank.widget.tools.dayMillis
import com.frank.widget.tools.lastDayOfMonthTime

interface IFlowModel : IScheduleModel {
    val sortValue: Long
}

data class MonthText(
    override val beginTime: Long,
) : IFlowModel {
    override val sortValue: Long = beginTime
    override val endTime: Long = beginTime.calendar.lastDayOfMonthTime
}

data class WeekText(
    override val beginTime: Long,
) : IFlowModel {
    override val sortValue: Long = beginTime + 1
    override val endTime: Long = beginTime + 7 * dayMillis
}

data class FlowDailySchedules(
    override val beginTime: Long,
    val schedules: List<IScheduleModel>
) : IFlowModel {
    override val sortValue: Long = beginTime + 2
    override val endTime: Long = beginTime + dayMillis
}