package idp.android.debugging

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.sandbox.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import idp.android.room.Item
import idp.android.room.ItemDb
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.random.Random


class DebugActivity : ComponentActivity() {
    lateinit var db: ItemDb

    // start 1; after onStart from foreground
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                DebugApp()
            }
        }
    }

    private fun init() {
        db = Room.databaseBuilder(
            applicationContext,
            ItemDb::class.java,
            "item-db"
        ).build()
    }

    // start 2; after foreground
    override fun onStart() {
        super.onStart()
    }

    // start 3; after onStart
    override fun onResume() {
        super.onResume()
    }

    // stop 1 to foreground
    override fun onPause() {
        super.onPause()
    }

    // stop 2 after onPause to foreground
    override fun onStop() {
        super.onStop()
    }

    // ignores??? After long sleep
    override fun onRestart() {
        super.onRestart()
    }

    // ignores??? GarbageCollector sleeping
    override fun onDestroy() {
        super.onDestroy()
    }

    // after onStop
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    // ignores???
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }
}

@SuppressLint("NewApi")
@Composable
fun DebugApp(
    modifier: Modifier = Modifier
) {
    val activity = LocalContext.current as DebugActivity
    var counter by remember {
        mutableIntStateOf(0)
    }
    var isTestChanged by remember {
        mutableStateOf(false)
    }
    val networkScope = rememberCoroutineScope { Dispatchers.IO }
    val roomScope = rememberCoroutineScope { Dispatchers.IO }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Hello World!",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        repeat(3) {
            DefaultButton {
                randomFunTriple()
            }
        }
        DefaultButton(
            text = "Counter value: $counter"
        ) {
            counter++
        }
        DefaultButton(
            text = stringResource(
                if (isTestChanged) {
                    R.string.true_text
                } else {
                    R.string.false_text
                }
            )
        ) {
            isTestChanged = !isTestChanged
        }
        AsyncButton()
        Button(
            onClick = {
                networkScope.launch {
                    networkCall()
                }
            }
        ) {
            Text(text = "Network call")
        }
        Button(
            onClick = {
                roomScope.launch {
                    val dao = activity.db.itemDao()
                    val newItem = Item(
                        name = "Hi",
                        price = 200.0,
                    )
                    val oldItem = dao.getItemByName(newItem.name)
                    activity.db.itemDao().insertNewDeleteOld(newItem, oldItem)
                }
            }
        ) {
            Text(text = "Room transaction")
        }
    }
}

@SuppressLint("NewApi")
suspend fun networkCall() {
    val url = URL("https://www.android.com/")
    val urlConnection = url.openConnection() as HttpURLConnection
    try {
        val inputStream: InputStream = BufferedInputStream(urlConnection.inputStream)
        inputStream.close()
    } finally {
        urlConnection.disconnect()
    }
    delay(100)
}

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String = randomInt().toString(),
    onClick: () -> Unit = {},
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text)
    }
}

enum class Status {
    LOADING,
    DONE,
    NONE,
    CANCELED,
}

@Composable
fun AsyncButton(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(48.dp),
) {
    val scope = rememberCoroutineScope()
    var state by remember {
        mutableStateOf(Status.NONE)
    }

    Button(
        onClick = {
            if (state != Status.LOADING) {
                state = Status.LOADING
                scope.launch {
                    hardWork()
                    state = Status.DONE
                }
            } else {
                scope.cancel()
                state = Status.CANCELED
            }
        },
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (state) {
                Status.LOADING -> {
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(24.dp)
                    )
                }

                else -> {
                    Text(text = state.name)
                }
            }
        }
    }
}

suspend fun hardWork() {
    delay(3000L)
}

fun randomInt(): Int = Random.nextInt(0, 10)

fun randomFunTriple(
    value: Int = randomInt()
) {
    when {
        value % 3 == 0 -> {
            randomFunTriple()
        }

        else -> {
            randomFunDouble()
        }
    }
}

fun randomFunDouble(
    value: Int = randomInt()
) {
    when {
        value % 2 == 0 -> {
            randomFunDouble()
        }

        else -> {
            return
        }
    }
}

@Preview
@Composable
fun DebugAppPreview() {
    DebugApp()
}