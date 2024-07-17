package all

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun main() {
    val result = formatCalendarDate(1705501205337L)
    println(result)
}

fun formatCalendarDate(date: Long): String {
    val sdf =
        SimpleDateFormat(
            "HH:mm",
            Locale.getDefault(),
        )
    sdf.timeZone = TimeZone.getTimeZone("GMT")
    return sdf.format(date)
}