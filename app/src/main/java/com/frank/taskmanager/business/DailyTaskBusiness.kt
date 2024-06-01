package com.frank.taskmanager.business

import androidx.fragment.app.FragmentManager
import com.frank.taskmanager.ui.DeleteOptionFragment
import com.frank.taskmanager.ui.UpdateOptionFragment
import com.frank.widget.base.DailyTaskModel
import com.frank.widget.base.IScheduleModel
import com.frank.widget.base.RepeatMode

interface DailyTaskBusiness {
    suspend fun saveCreateDailyTask(
        model: DailyTaskModel,
        block: (created: List<IScheduleModel>) -> Unit
    )

    suspend fun getDailyTasks(beginTime: Long, endTime: Long): List<IScheduleModel>

    suspend fun removeDailyTasks(
        model: DailyTaskModel,
        option: DeleteOptionFragment.DeleteOption,
    ) : List<Long>

    suspend fun updateDailyTasks(
        model: DailyTaskModel,
        option: UpdateOptionFragment.UpdateOption,
    ) : List<Long>

    fun showCreateFragment(
        fragmentManager: FragmentManager,
        beginTime: Long? = null,
        duration: Long? = null,
        title: String? = null,
        repeatMode: RepeatMode = RepeatMode.Never,
        onCreated: (created: List<IScheduleModel>) -> Unit = {}
    )
}