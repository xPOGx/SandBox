package idp.ext

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sandbox.R

object Theme {
    val typography = MyTypography
    val colors = MyColors
    val sizes = MySizes
}

object MyTypography {
    private val robotoFamily =
        FontFamily(
            listOf(
                Font(R.font.roboto_regular_400, FontWeight.W400),
                Font(R.font.roboto_medium_500, FontWeight.W500),
            )
        )

    val regular =
        TextStyle(
            fontFamily = robotoFamily,
            fontSize = 10.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 12.sp
        )

    val medium =
        TextStyle(
            fontFamily = robotoFamily,
            fontSize = 10.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 12.sp
        )
}

object MyColors {
    val black = Color(0xFF313333)
    val grey300 = Color(0xFF8F9090)
    val blue = Color(0xFF4B84F1)
}

object MySizes {
    val size2: Dp = 2.dp
    val size8: Dp = 8.dp
    val size24: Dp = 24.dp
    val size54: Dp = 54.dp
}
