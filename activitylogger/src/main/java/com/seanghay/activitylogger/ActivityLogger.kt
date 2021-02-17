package com.seanghay.activitylogger

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RestrictTo
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.text.SimpleDateFormat
import java.util.*


/**
 * This class is used only for testing purpose only.
 * Logs created activity and fragment lifecycle.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY)
class ActivityLogger(
    application: Application,
    private val tag: String,
) {

    private val fragmentLifecycleCallbacks = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) =
            log(f, "Attached")

        override fun onFragmentActivityCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) = log(f, "ActivityCreated")

        override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) =
            log(f, "ViewDestroyed")

        override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) = log(f, "Destroyed")
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) = log(f, "ViewCreated")

        override fun onFragmentCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) = log(f, "Created")
    }

    private val activityLifecycleCallbacks: ActivityLifecycleCallbacks =
        object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
                log(activity, "Created")
                if (activity is AppCompatActivity) {
                    activity.supportFragmentManager
                        .registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, true)
                }
            }

            override fun onActivityStarted(activity: Activity) = log(activity, "Started")
            override fun onActivityResumed(activity: Activity) = log(activity, "Resumed")
            override fun onActivityPaused(activity: Activity) = log(activity, "Paused")
            override fun onActivityStopped(activity: Activity) = log(activity, "Stopped")

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) =
                log(activity, "SaveInstanceState")

            override fun onActivityDestroyed(activity: Activity) {
                log(activity, "Destroyed")
                if (activity is AppCompatActivity) {
                    activity.supportFragmentManager
                        .unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks)
                }
            }
        }

    init {
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }

    private fun log(fragment: Fragment, state: String) {
        val parentActivityName = fragment.activity?.javaClass?.simpleName ?: "<Unattached>"
        val fragmentName = fragment.javaClass.simpleName
        Log.i(
            tag,
            String.format(
                LOG_FRAGMENT_FORMAT,
                parentActivityName,
                fragmentName,
                state,
                timestamp()
            )
        )
    }

    private fun log(activity: Activity, state: String) {
        if (activity !is AppCompatActivity) return
        val className = activity.javaClass.simpleName
        Log.i(tag, String.format(LOG_FORMAT, className, state, timestamp()))
    }

    companion object {

        private val simpleDateFormat = SimpleDateFormat("hh:mm:ss aa", Locale.ENGLISH)

        private fun timestamp(): String {
            val date = Date(System.currentTimeMillis())
            return simpleDateFormat.format(date)
        }

        private const val LOG_FORMAT = "Activity: %s [%s] - %s"
        private const val LOG_FRAGMENT_FORMAT = "Activity: %s -> Fragment: %s [%s] - %s"
    }
}