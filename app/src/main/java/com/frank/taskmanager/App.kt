package com.frank.taskmanager

import android.app.Application
import com.frank.widget.ScheduleConfig

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        self = this
        ScheduleConfig.app = self
    }

    companion object {
        lateinit var self: Application
            private set
    }
}