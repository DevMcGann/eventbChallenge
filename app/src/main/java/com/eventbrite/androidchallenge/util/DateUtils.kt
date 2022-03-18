package com.eventbrite.androidchallenge.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun stringToDate(dateString: String, pattern: String): Date?{
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        var convertedDate: Date? = null
        try {
            convertedDate = sdf.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return convertedDate
    }

    fun getStringDate(date: Date, pattern: String = Constants.DATE_FORMAT): String? {
        var stringDate = ""
        try {
            val format = SimpleDateFormat(pattern, Locale.getDefault())
            stringDate = format.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return stringDate
    }


}