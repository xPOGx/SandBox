package idp.android.recycler

import android.media.MediaPlayer
import android.widget.LinearLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import idp.ext.Mock
import idp.ext.Music

@Composable
fun MusicScreen(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val linearManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    val musicMap: Map<Music, MediaPlayer> = remember {
        try {
            Mock.musicList.associateWith {
                MediaPlayer.create(context, it.musicRes)
            }
        } catch (_: Exception) {
            // stub for preview
            mapOf()
        }
    }

    var currentPlayer: MediaPlayer? by remember { mutableStateOf(null) }

    var clear: () -> Unit = {}

    val musicAdapter: MusicAdapter =
        MusicAdapter {
            val player = musicMap[it]
            player ?: return@MusicAdapter

            with(player) {
                if (this == currentPlayer) {
                    resetPlayer(currentPlayer)
                    currentPlayer = null
                } else {
                    resetPlayer(currentPlayer)
                    currentPlayer = player

                    start()

                    setOnCompletionListener {
                        seekTo(0)
                        clear()
                        currentPlayer = null
                    }
                }
            }
        }.apply {
            submitList(Mock.musicList)
        }

    LaunchedEffect(Unit) {
        clear = { musicAdapter.clear() }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Text(
            text = "Music place",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp)
        )
        AndroidView(
            factory = {
                RecyclerView(context).apply {
                    layoutManager = linearManager
                    addItemDecoration(DividerItemDecoration(this.context, LinearLayout.VERTICAL))
                    itemAnimator = DefaultItemAnimator()
                    adapter = musicAdapter
                }
            },
            modifier = Modifier.fillMaxSize()
        ) {

        }
    }
}

private fun resetPlayer(player: MediaPlayer?) {
    player?.apply {
        pause()
        seekTo(0)
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun MusicScreenPreview() {
    MusicScreen()
}
