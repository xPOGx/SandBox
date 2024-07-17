package idp.android.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import idp.ext.Mock

@Composable
fun Screen(
    modifier: Modifier = Modifier,
) {
    var items = remember { Mock.items.toMutableList() }
    var selectedItem by remember { mutableStateOf(items.first()) }

    Scaffold(
        bottomBar = {
            BottomBar(
                selectedItem = selectedItem,
                items = items,
            ) { item ->
                if (item.priority) {
                    val currentIndex = items.indexOf(item)
                    if (currentIndex != -1) {
                        val updateItem = items[currentIndex].copy(priority = false)
                        items[currentIndex] = updateItem
                        selectedItem = updateItem
                    } else {
                        selectedItem = item
                    }
                } else {
                    selectedItem = item
                }
            }
        },
    ) { paddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier =
                modifier
                    .fillMaxSize()
                    .padding(paddingValues),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = selectedItem.text,
                    style = MaterialTheme.typography.headlineLarge,
                )
                DebugMenu {
                    when (it) {
                        DebugAction.Reset -> {
                            items = Mock.items.toMutableList()
                            selectedItem = items.first()
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ScreenPreview() {
    Screen()
}
