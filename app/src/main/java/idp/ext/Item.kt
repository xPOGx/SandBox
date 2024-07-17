package idp.ext

import androidx.annotation.DrawableRes

data class Item(
    @DrawableRes val iconRes: Int,
    val text: String,
    val priority: Boolean = false,
)
