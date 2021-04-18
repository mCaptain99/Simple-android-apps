package com.example.todolist

import java.util.*

class Utils {
    companion object {
        fun toDateString(dayOfMonth: Int, monthOfYear: Int, year: Int): String {
            val strDayOfMonth = if (dayOfMonth < 10) "0" + dayOfMonth.toString() else dayOfMonth.toString()
            val strMonthOfYear = if (monthOfYear < 10) "0" + monthOfYear.toString() else monthOfYear.toString()
            return strDayOfMonth + "." + strMonthOfYear + "." + year.toString()
        }
        fun toTimeString(hour: Int, minute: Int) : String{
            val strHour = if (hour < 10) "0" + hour.toString() else hour.toString()
            val strMinute = if (minute < 10) "0" + minute.toString() else minute.toString()
            return strHour + ":" + strMinute
        }
        fun toDateTimeString(c: Calendar) : String{
            return toDateString(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH), c.get(Calendar.YEAR)) + "  " +
                    toTimeString(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE))
        }
    }
}