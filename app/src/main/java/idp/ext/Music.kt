package idp.ext

import android.media.MediaPlayer
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.example.sandbox.R
import kotlin.time.DurationUnit
import kotlin.time.toDuration

data class Music(
    val name: String = "Music",
    val artist: String = "Artist",
    @DrawableRes val imageRes: Int = R.drawable.ic_launcher_foreground,
    @RawRes val musicRes: Int = R.raw.loopazon,
    var player: MediaPlayer? = null,
    var maxTime: Int = 0,
)

// 14545
fun formattedTime(time: Int): String =
    time.toDuration(DurationUnit.MILLISECONDS).toString()
