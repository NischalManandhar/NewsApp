import DateContracts.FORMAT_DAY_SHORT_MONTH_YEAR
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @return date in one of the following formats from the provided timestamp in milliseconds
 * - If day >= 1, output: "20 Apr 2020"
 * - Other output includes:
 *      - 23h ago or 23h 20m ago
 *      - 5m ago
 *      - Just now
 */
fun Long.toNewsTimeFormat(): String {
    val time = System.currentTimeMillis() - this
    val hour = TimeUnit.MILLISECONDS.toHours(time)
    val min = (TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(hour))
    return when {
        hour > 24 ->
            toFullDateFormat(FORMAT_DAY_SHORT_MONTH_YEAR)
        hour >= 1 ->
            StringBuilder()
                .append(hour.toString())
                .append("h ago")
                .toString()
        min >= 1 ->
            StringBuilder()
                .append(min.toString())
                .append("m ago")
                .toString()
        else -> {
            StringBuilder()
                .append("Just now")
                .toString()
        }
    }
}

/**
 * @return date in 20 Apr 2020 format from timestamp in milliseconds
 */
fun Long.toFullDateFormat(resultFormat: String = FORMAT_DAY_SHORT_MONTH_YEAR): String {
    return try {
        val dateFormat = SimpleDateFormat(resultFormat, Locale.getDefault())
        val date = Date(this)
        dateFormat.format(date)
    } catch (e: Exception) {
        ""
    }
}

object DateContracts {
    const val FORMAT_DAY_SHORT_MONTH_YEAR: String = "dd MMM yyyy"
}
