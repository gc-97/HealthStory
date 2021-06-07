package com.example.kotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.kotlin.util.NotificationUtil
import com.example.kotlin.util.PrefUtil

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationUtil.showTimerExpired(context)

        PrefUtil.setTimerState(TimerActivity.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}
