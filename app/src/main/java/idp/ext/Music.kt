package idp.ext

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.example.sandbox.R

data class Music(
    val name: String = "Music",
    val artist: String = "Artist",
    @DrawableRes val imageRes: Int = R.drawable.ic_launcher_foreground,
    @RawRes val musicRes: Int = R.raw.loopazon,
)
