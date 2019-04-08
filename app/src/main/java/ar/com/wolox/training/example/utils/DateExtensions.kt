package ar.com.wolox.training.example.utils

import ar.com.wolox.training.example.utils.Constants.DATE_FORMAT
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.ocpsoft.prettytime.PrettyTime

fun getTimeFromDate(date: String?): String {
    return try {
        val formatter = DateTimeFormat.forPattern(DATE_FORMAT)
        val dt = LocalDate.parse(date, formatter)
        return PrettyTime().format(dt.toDate())
    } catch (e: Exception) {
        date.orEmpty()
    }
}