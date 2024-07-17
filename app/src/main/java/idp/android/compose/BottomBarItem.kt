package idp.android.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.sandbox.R
import idp.ext.Item
import idp.ext.Theme

@Composable
fun BottomBarItem(
    modifier: Modifier = Modifier,
    item: Item,
    selected: Boolean = false,
    isNotify: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    val textStyle: TextStyle
    val textColor: Color
    val tint: Color

    if (selected) {
        textStyle = Theme.typography.medium
        textColor = Theme.colors.black
        tint = Theme.colors.blue
    } else {
        textStyle = Theme.typography.regular
        textColor = Theme.colors.grey300
        tint = Theme.colors.grey300
    }

    Surface(
        color = Color.White,
        modifier =
            modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    enabled = onClick != null,
                    onClick = { onClick?.invoke() }
                ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box {
                Icon(
                    painter = painterResource(item.iconRes),
                    contentDescription = null,
                    tint = tint,
                    modifier = Modifier.size(Theme.sizes.size24),
                )
                if (isNotify) {
                    Dot(color = Theme.colors.blue)
                }
            }
            Spacer(modifier = Modifier.height(Theme.sizes.size2))
            Text(
                text = item.text,
                style = textStyle,
                color = textColor,
            )
        }
    }
}

@Composable
fun BoxScope.Dot(
    modifier: Modifier = Modifier,
    color: Color,
) {
    Box(
        modifier =
            modifier
                .align(Alignment.TopEnd)
                .size(Theme.sizes.size8)
                .background(
                    color = color,
                    shape = CircleShape,
                )
    )
}

@Preview(showBackground = true)
@Composable
private fun BottomBarItemPreview() {
    BottomBarItem(
        item =
            Item(
                iconRes = R.drawable.ic_home_24,
                text = "Home",
            ),
        isNotify = true,
    )
}
