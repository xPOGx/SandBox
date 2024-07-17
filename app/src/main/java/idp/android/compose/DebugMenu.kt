package idp.android.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DebugMenu(
    modifier: Modifier = Modifier,
    onDebugAction: ((DebugAction) -> Unit)? = null,
) {
    Column(modifier) {
        Button(
            onClick = { onDebugAction?.invoke(DebugAction.Reset) }
        ) {
            Text(text = "Reset Notification")
        }
    }
}

sealed class DebugAction {
    object Reset : DebugAction()
}