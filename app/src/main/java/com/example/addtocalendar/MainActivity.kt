package com.example.addtocalendar

import android.content.Intent
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract
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
                set(2022, 4, 20, 7, 30, 0)
                timeInMillis
            }
            val endMillis: Long = Calendar.getInstance().run {
                set(2022, 4, 21, 9, 40, 0)
                timeInMillis
            }
            try {
                val cal: Calendar = Calendar.getInstance()
                val timeZone: String = TimeZone.getDefault().id
                val intent = Intent(Intent.ACTION_EDIT)
                intent.type = "vnd.android.cursor.item/event"
                intent.putExtra(CalendarContract.Events.CALENDAR_ID, 123)
                intent.putExtra(CalendarContract.Events.TITLE, "Toffee Star Search Grand Finale (HD)")
                intent.putExtra(CalendarContract.Events.DESCRIPTION, "দুইমাসের তুমুল প্রতিদ্বন্দ্বিতা! অসাধারণ সব পারফর্ম্যান্স! শেষপর্যন্ত কে হলো ১ম টফি স্টার?")
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
                intent.putExtra(CalendarContract.ACTION_EVENT_REMINDER, true)
                intent.putExtra(CalendarContract.Events.EVENT_TIMEZONE ,timeZone)
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION,"Dhaka")
                intent.putExtra(CalendarContract.Events.ALL_DAY, false)
                intent.putExtra(CalendarContract.Events.RRULE, "FREQ=HOURLY")
                intent.putExtra(CalendarContract.Events.HAS_ALARM, 1)
                intent.putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_FREE)
                intent.putExtra(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT)
                intent.putExtra(CalendarContract.Reminders.MINUTES, 10)
//                intent.putExtra(CalendarContract.Events.EVENT_COLOR, Color.MAGENTA)
//                intent.putExtra(CalendarContract.Events.CALENDAR_COLOR, Color.GRAY)
//                intent.putExtra(CalendarContract.Events.DISPLAY_COLOR, Color.BLACK)
//                intent.putExtra(CalendarContract.Events.DURATION ,"+P1H")
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}