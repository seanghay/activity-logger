package com.seanghay.activitylogger

import android.app.Application
import android.content.Context
import androidx.startup.Initializer

class ActivityLoggerInitializer : Initializer<ActivityLogger> {

    override fun create(context: Context): ActivityLogger {
        return ActivityLogger(context.applicationContext as Application, "ActivityLogger")
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}