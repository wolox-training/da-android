package ar.com.wolox.android.example.utils

import ar.com.wolox.android.example.utils.Constants.DATE_FORMAT
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.Years
import org.joda.time.format.DateTimeFormat

fun getTimeFromDate(date: String?): String {
    return try {
        val formatter = DateTimeFormat.forPattern(DATE_FORMAT)
        val dt = LocalDate.parse(date, formatter)
        val years = Years.yearsBetween(dt, DateTime.now().toLocalDate()).years

        years.toString()
    } catch (e: Exception) {
        date.orEmpty()
    }
}