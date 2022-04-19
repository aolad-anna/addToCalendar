package com.example.addtocalendar

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.parseColor
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val CButton: Button = findViewById<View>(R.id.add_to_calendar) as Button
        CButton.setOnClickListener {
            val startMillis: Long = Calendar.getInstance().run {
                set(2022, 4, 20, 12, 30, 0)
                timeInMillis
            }
            val endMillis: Long = Calendar.getInstance().run {
                set(2022, 4, 20, 13, 40, 0)
                timeInMillis
            }
            try {
                val cal: Calendar = Calendar.getInstance()
                val eventID: Long = 221
                val calID: Long = 3
                val timeZone: String = TimeZone.getDefault().id
                val intent = Intent(Intent.ACTION_INSERT)
                    .setData(Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
                    .putExtra(Events.TITLE, "Streaming Toffee Star Search Grand Finale (HD)")
                    .putExtra(Events.DESCRIPTION, "দুইমাসের তুমুল প্রতিদ্বন্দ্বিতা! অসাধারণ সব পারফর্ম্যান্স! শেষপর্যন্ত কে হলো ১ম টফি স্টার?")
                    .putExtra(Events.EVENT_LOCATION, "Dhaka")
                    .putExtra(Events.CALENDAR_ID, calID)
                    .putExtra(Events.CALENDAR_COLOR, Color.MAGENTA)
                    .putExtra(Events.EVENT_COLOR, Color.MAGENTA)
                    .putExtra(Events.DISPLAY_COLOR, Color.MAGENTA)
                    .putExtra(Events.EVENT_TIMEZONE ,timeZone)
                    .putExtra(Events.ALL_DAY, false)
                    .putExtra(Events.HAS_ALARM, 1)
                    .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_FREE)
                    .putExtra(CalendarContract.Reminders.EVENT_ID, eventID)
                    .putExtra(CalendarContract.Reminders.MINUTES, 10)
                    .putExtra(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT)
                    //.putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com")
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}