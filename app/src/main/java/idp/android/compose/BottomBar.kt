package idp.android.compose

import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import idp.ext.Item
import idp.ext.Mock
import idp.ext.Theme

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    selectedItem: Item,
    items: List<Item> = emptyList(),
    onClick: ((Item) -> Unit)? = null,
) {
    BottomAppBar(
        actions = {
            items.forEach { item ->
                BottomBarItem(
                    selected = item == selectedItem,
                    item = item,
                    isNotify = item.priority,
                    modifier = Modifier.weight(1f)
                ) { onClick?.invoke(item) }
            }
        },
        containerColor = Color.White,
        modifier =
            modifier
                .height(Theme.sizes.size54)
                .shadow(
                    elevation = Theme.sizes.size8,
                ),
    )
}

@Preview
@Composable
private fun BottomBarPreview() {
    BottomBar(
        selectedItem = Mock.item,
        items = Mock.items
    )
}
